package com.nyh.controller;


import com.nyh.exception.DuplicateCodeException;
import com.nyh.pojo.BootStrapTableExpress;
import com.nyh.pojo.Express;
import com.nyh.pojo.Message;
import com.nyh.pojo.ResultData;
import com.nyh.service.ExpressService;
import com.nyh.utils.DateFormatUtil;
import com.nyh.utils.JSONUtil;
import com.nyh.utils.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody // 该注解声明下的类所有返回字符串的方法都不去查找跟字符串同名的视图，而是直接返回字符串本身
@RequestMapping("/express")
public class ExpressController {

    @Resource // 自动注入
    private ExpressService expressService;

    DateFormatUtil dateFormatUtil = new DateFormatUtil();

    /**
     * 快件列表数据的获取
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/list.do")
    public String list(HttpServletRequest request,HttpServletResponse response){
        //1.    获取查询数据的起始索引值
        int offset = Integer.parseInt(request.getParameter("offset"));
        //2.    获取当前页要查询的数据量
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        //3.    进行查询
        List<Express> list = expressService.findAll(true,offset,pageNumber);
        List<BootStrapTableExpress> list2 = new ArrayList<>();
        for(Express e:list){
            String inTime = dateFormatUtil.format(e.getIntime());
            String outTime = e.getOuttime()==null?"未出库":dateFormatUtil.format(e.getOuttime());
            String status = e.getStatus()==0?"待取件":"已取件";
            String code = e.getCode()==null?"已取件":e.getCode();
            BootStrapTableExpress e2 = new BootStrapTableExpress(e.getId(),e.getNumber(),e.getUsername(),e.getUserphone(),e.getCompany(),code,inTime,outTime,status,e.getSysPhone());
            list2.add(e2);
        }

        System.out.println(list2);
        List<Map<String,Integer>> console = expressService.console();
        Integer total = console.get(0).get("data1_size");
        //4.    将集合封装为  bootstrap-table识别的格式
        ResultData<BootStrapTableExpress> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);  // 由于没写console，先给他写死
        String json = JSONUtil.toJSON(data);
        return json;
    }

    /**
     * 根据快递单号查询快递的请求
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/find.do")
    public String find(HttpServletRequest request,HttpServletResponse response){
        String number = request.getParameter("number"); // 获取请求参数，快递单号
        Express e = expressService.findByNumber(number);
        Message msg = new Message();
        if(e == null){
            msg.setStatus(-1);
            msg.setResult("单号不存在");
        }else{
            request.getSession().setAttribute("express",e); // 将查到得即将准备修改的快递存储到session中
            msg.setStatus(0);
            msg.setResult("查询成功");
            msg.setData(e);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 快件的录入
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/insert.do")
    public String insert(HttpServletRequest request,HttpServletResponse response){
        // 获取提交的请求参数
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        Express e = new Express(number,username,userPhone,company, UserUtil.getUserPhone(request.getSession()));
        boolean flag = false;
        try {
            flag = expressService.insert(e);
        } catch (DuplicateCodeException duplicateCodeException) {
            duplicateCodeException.printStackTrace();
        }
        Message msg = new Message();
        if(flag){
            //录入成功
            msg.setStatus(0);
            msg.setResult("快递录入成功！");
        }else{
            //录入失败
            msg.setStatus(-1);
            msg.setResult("快递录入失败，单号重复！");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 快件的修改
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/update.do")
    public String update(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String number = request.getParameter("number");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String userPhone = request.getParameter("userPhone");
        int status = Integer.parseInt(request.getParameter("status"));

        // 重session中获取快递信息
        Express newExpress = (Express) request.getSession().getAttribute("express");
        newExpress.setNumber(number);
        newExpress.setCompany(company);
        newExpress.setUsername(username);
        newExpress.setUserphone(userPhone);
        newExpress.setStatus(status);

        // 如果快递状态修改为已签收，那么就需要设置当前时间为出库时间
        if(status==1){
            newExpress.setCode(null); // 将取件码置为空
            newExpress.setOuttime(new Date());
        }else{
            // 快递状态不能由已取件变为未取件
            newExpress.setOuttime(null);
        }
        boolean flag = expressService.update(id, newExpress);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("修改成功");
        }else{
            msg.setStatus(-1);
            msg.setResult("修改失败");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 快件的删除
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delete.do")
    public String delete(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = expressService.delete(id);
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
