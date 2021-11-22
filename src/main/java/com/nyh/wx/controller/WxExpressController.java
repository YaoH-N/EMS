package com.nyh.wx.controller;

import com.nyh.exception.DuplicateCodeException;
import com.nyh.pojo.BootStrapTableExpress;
import com.nyh.pojo.Express;
import com.nyh.pojo.Message;
import com.nyh.pojo.User;
import com.nyh.service.ExpressService;
import com.nyh.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
@ResponseBody // 该注解声明下的类所有返回字符串的方法都不去查找跟字符串同名的视图，而是直接返回字符串本身
@RequestMapping("/wx/express")
public class WxExpressController {

    @Resource
    private ExpressService expressService;

    private DateFormatUtil dateFormatUtil=new DateFormatUtil();

    /**
     * 微信端根据手机号码查询快递
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findExpressByUserPhone.do")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response) {
        User wxUser = UserUtil.getWxUser(request.getSession());
        // 获取session中存储的当前登录用户的手机号码
        String userphone = wxUser.getUserphone();
        // 根据手机号码查询用户的所有快递信息
        List<Express> expresses = expressService.findByUserPhone(userphone);

        Message msg = new Message();

        if(expresses==null){
            // 说明改用户没有快递
            msg.setStatus(-5);
            msg.setData("很抱歉，暂时没有您的快递信息!");
            return JSONUtil.toJSON(msg);
        }
        List<BootStrapTableExpress> list2 = new ArrayList<>();
        for(Express e:expresses){
            String inTime = dateFormatUtil.format(e.getIntime());
            String outTime = e.getOuttime()==null?"未出库":dateFormatUtil.format(e.getOuttime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserphone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }


        if (expresses.size() == 0) {
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
            // 从对象列表中挑选出status为0的快递对象，并按入库时间进行排序
            Stream<BootStrapTableExpress> status0Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("待取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = dateFormatUtil.toTime(o1.getInTime());
                long o2Time = dateFormatUtil.toTime(o2.getInTime());
                return (int)(o1Time-o2Time);
            });
            Stream<BootStrapTableExpress> status1Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("已取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = dateFormatUtil.toTime(o1.getInTime());
                long o2Time = dateFormatUtil.toTime(o2.getInTime());
                return (int)(o1Time-o2Time);
            });
            Object[] s0 = status0Express.toArray();
            Object[] s1 = status1Express.toArray();
            Map data = new HashMap<>();
            data.put("status0",s0);
            data.put("status1",s1);
            msg.setData(data);
        }
        return JSONUtil.toJSON(msg);
    }

    /**
     * 微信端根据手机号码和快递状态查询快递信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(("/findByUserPhoneAndStatus.do"))
    public String findByUserPhoneAndStatus(HttpServletRequest request, HttpServletResponse response) {
        String userPhone = request.getParameter("userPhone");
        // 根据手机号码查询用户未取件的快递
        List<Express> list = expressService.findByUserPhoneAndStatus(userPhone, 0);
        // 格式化list为list2，为了里面的数据能够方便的显示
        List<BootStrapTableExpress> list2 = new ArrayList<>();
        for(Express e:list){
            String inTime = dateFormatUtil.format(e.getIntime());
            String outTime = e.getOuttime()==null?"未出库":dateFormatUtil.format(e.getOuttime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserphone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }

        for (BootStrapTableExpress bootStrapTableExpress : list2) {
            System.out.println(bootStrapTableExpress);
        }

        Message msg = new Message();
        if (list.size() == 0) {
            msg.setStatus(-1);
            msg.setResult("未查询到快递");
        } else {
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(list2);
        }
        return JSONUtil.toJSON(msg);
    }

    /**
     * 更新快递状态的请求
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateStatus.do")
    public String updateExpressStatus(HttpServletRequest request,HttpServletResponse response){
        String code = request.getParameter("code");
        boolean flag = expressService.updateStatus(code);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("取件成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("取件码不存在,确认收货失败!");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 根据取件码查询快递信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findExpressByCode.do")
    public String findExpressByCode(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求参数
        String code = request.getParameter("code");
        // 根据取货码查询快递
        Express e = expressService.findByCode(code);
        System.out.println(code+e);
        BootStrapTableExpress e2 =null;
        if(e!=null){
            e2 = new BootStrapTableExpress(e.getId(), e.getNumber(), e.getUsername()
                    , e.getUserphone(), e.getCompany(), e.getCode(),
                    dateFormatUtil.format(e.getIntime()), e.getOuttime()==null?"未出库":dateFormatUtil.format(e.getOuttime()),e.getStatus()==0?"0":"1", e.getSysPhone());
        }
        Message msg = new Message();
        if (e == null) {
            msg.setStatus(-1);
            msg.setResult("取件码不存在!");
        } else {
            msg.setStatus(0);
            msg.setResult("查询成功!");
            msg.setData(e2);
        }
        return JSONUtil.toJSON(msg);
    }

    /**
     * 录入快递微信端
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/insertExpress.do")
    public String insertExpress(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求参数
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String userphone = request.getParameter("userphone");
        boolean insert = false;
        try {
            // 录入快递
            insert = expressService.insert(new Express(number, username, userphone, company, UserUtil.getUserPhone(request.getSession())));
        } catch (DuplicateCodeException e) {
            e.printStackTrace();
        }
        Message msg = new Message();
        if (insert) {
            msg.setStatus(0);
            msg.setResult("快递录入成功!");
            // 发送短信
            String code = RandomUtil.getCode()+"";
            TencentSMSUtil.send1(userphone, code);
            msg.setData("验证码已发送："+code);
        } else {
            msg.setStatus(-1);
            msg.setResult("快递录入失败!");
        }
        return JSONUtil.toJSON(msg);
    }

    /**
     * 根据录入人手机号码进行历史快递查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findExpressBySysPhone.do")
    public String findBySysPhone(HttpServletRequest request, HttpServletResponse response) {
        // 获取当前登录的用户信息
        User wxUser = UserUtil.getWxUser(request.getSession());
        String userphone = wxUser.getUserphone();
        // 根据手机号码查询快递信息
        List<Express> expresses = expressService.findBySysPhone(userphone);
        List<BootStrapTableExpress> list2 = new ArrayList<>();
        for(Express e:expresses){
            String inTime = dateFormatUtil.format(e.getIntime());
            String outTime = e.getOuttime()==null?"未出库":dateFormatUtil.format(e.getOuttime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserphone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }

        Message msg = new Message();
        if (expresses.size() == 0) {
            // 没有该手机号对应的快递信息
            msg.setStatus(-1);
        } else {
            msg.setStatus(0);
            // 从对象列表中挑选出status为0的快递对象，并按入库时间进行排序
            Stream<BootStrapTableExpress> status0Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("待取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = dateFormatUtil.toTime(o1.getInTime());
                long o2Time = dateFormatUtil.toTime(o2.getInTime());
                return (int)(o1Time-o2Time);
            });
            Stream<BootStrapTableExpress> status1Express = list2.stream().filter(express -> {
                if (express.getStatus().equals("已取件")) {
                    return true;
                } else {
                    return false;
                }
            }).sorted((o1,o2) -> {
                long o1Time = dateFormatUtil.toTime(o1.getInTime());
                long o2Time = dateFormatUtil.toTime(o2.getInTime());
                return (int)(o1Time-o2Time);
            });
            Object[] s0 = status0Express.toArray();
            Object[] s1 = status1Express.toArray();
            Map data = new HashMap<>();
            data.put("status0",s0);
            data.put("status1",s1);
            msg.setData(data);
        }
        return JSONUtil.toJSON(msg);
    }
}


