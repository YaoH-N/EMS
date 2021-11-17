package com.nyh.controller;

import com.nyh.pojo.*;
import com.nyh.service.CourierService;
import com.nyh.utils.DateFormatUtil;
import com.nyh.utils.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody // 该注解声明下的类所有返回字符串的方法都不去查找跟字符串同名的视图，而是直接返回字符串本身
@RequestMapping("/courier")
public class CourierController {

    @Resource // 通过spring ioc 容器自动注入 CourierService 对象
    private CourierService courierService;

    private DateFormatUtil dateFormatUtil = new DateFormatUtil();

    @RequestMapping("/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response){
        //1.    获取查询数据的起始索引值
        int offset = Integer.parseInt(request.getParameter("offset"));
        //2.    获取当前页要查询的数据量
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        //3.    进行查询
        List<Courier> list = courierService.findAll(true,offset,pageNumber);
        for (Courier courier : list) {
            System.out.println(courier);
        }
        List<BootStrapTableCourier> list2 = new ArrayList<>();
        for(Courier courier:list){
            String regTime = dateFormatUtil.format(courier.getRegtime());
            String preLogTime = courier.getPrelogtime()==null?"":dateFormatUtil.format(courier.getPrelogtime());
            BootStrapTableCourier e2 = new BootStrapTableCourier(courier.getId(),courier.getExname(),courier.getExphone(),courier.getIdcard(),courier.getExpassword(),courier.getTrannumber(),regTime,preLogTime);
            list2.add(e2);
        }

        Map<String,Integer> console = courierService.console();
        Integer total = console.get("data3_size");
        //4.    将集合封装为  bootstrap-table识别的格式
        ResultData<BootStrapTableCourier> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        String json = JSONUtil.toJSON(data);
        return json;
    }

    @RequestMapping("/insert.do")
    public String insert(HttpServletRequest request,HttpServletResponse response){
        String exName = request.getParameter("exName");
        String exPhone = request.getParameter("exPhone");
        String idCard = request.getParameter("idCard");
        String exPassWord = request.getParameter("exPassWord");
        /**
         * 录入之前先根据用户输入的手机号码查询快递员，看该手机号码是否已经注册过快递员
         */
        Message msg = new Message();
        Courier byExPhone = courierService.findByExPhone(exPhone);
        if(byExPhone!=null){
            msg.setStatus(-1);
            msg.setResult("很遗憾，该手机号已被注册");
        }else { // 准备录入用户
            Courier courier = new Courier(exName,exPhone,idCard,exPassWord);
            boolean flag = courierService.insert(courier);
            if(flag){
                //录入成功
                msg.setStatus(0);
                msg.setResult("快递员录入成功！");
            }else{
                //录入失败
                msg.setStatus(-1);
                msg.setResult("快递员录入失败！");
            }
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @RequestMapping("/find.do")
    public String find(HttpServletRequest request,HttpServletResponse response){
        String exPhone = request.getParameter("exPhone");
        Courier courier = courierService.findByExPhone(exPhone);
        Message msg = new Message();
        if(courier == null){
            msg.setStatus(-1);
            msg.setResult("不存在该手机号注册的快递员信息");
        }else{
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(courier);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @RequestMapping("/update.do")
    public String update(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String exName = request.getParameter("exName");
        String exPhone = request.getParameter("exPhone");
        String idCard = request.getParameter("idCard");
        String exPassword = request.getParameter("exPassword");

        /**
         * 更新之前先根据用户输入的手机号码查询快递员，看该手机号码是否已经注册过快递员
         */
        Message msg = new Message();
        Courier byExPhone = courierService.findByExPhone(exPhone);
        if(byExPhone!=null){
            msg.setStatus(-1);
            msg.setResult("很遗憾，该手机号已被注册");
        }else{ // 准备更新快递员信息
            Courier newCourier = new Courier();
            newCourier.setId(id);
            newCourier.setExname(exName);
            newCourier.setExphone(exPhone);
            newCourier.setIdcard(idCard);
            newCourier.setExpassword(exPassword);
            boolean flag = courierService.update(id, newCourier);
            if(flag){
                msg.setStatus(0);
                msg.setResult("修改成功");
            }else{
                msg.setStatus(-1);
                msg.setResult("修改失败");
            }
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }


    @RequestMapping("/delete.do")
    public String delete(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = courierService.delete(id);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("删除成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("删除失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }


}
