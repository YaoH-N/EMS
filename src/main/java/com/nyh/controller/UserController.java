package com.nyh.controller;

import com.nyh.pojo.*;
import com.nyh.service.ExpressService;
import com.nyh.service.UserService;
import com.nyh.utils.DateFormatUtil;
import com.nyh.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {

    @Resource // 通过spring ioc 容器自动注入 UserService 对象
    private UserService userService;

    private DateFormatUtil dateFormatUtil = new DateFormatUtil();

    @RequestMapping("/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response){
        //1.    获取查询数据的起始索引值
        int offset = Integer.parseInt(request.getParameter("offset"));
        //2.    获取当前页要查询的数据量
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        //3.    进行查询
        List<User> list = userService.findAll(true,offset,pageNumber);
        List<BootStrapTableUser> list2 = new ArrayList<>();
        for(User user:list){
            String regTime = dateFormatUtil.format(user.getRegtime());
            String preLogTime = user.getPrelogtime()==null?"":dateFormatUtil.format(user.getPrelogtime());
            BootStrapTableUser e2 = new BootStrapTableUser(user.getId(),user.getUsername(),user.getUserphone(),user.getIdcard(), user.getUserpwd(), regTime,preLogTime);
            list2.add(e2);
        }
        Map<String,Integer> console = userService.console();
        Integer total = console.get("data4_size");
        //4.    将集合封装为  bootstrap-table识别的格式
        ResultData<BootStrapTableUser> data = new ResultData<>();
        data.setRows(list2);
        data.setTotal(total);
        String json = JSONUtil.toJSON(data);
        return json;
    }

    @RequestMapping("/insert.do")
    public String insert(HttpServletRequest request,HttpServletResponse response){
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String idCard = request.getParameter("idCard");
        String userPwd = request.getParameter("userPwd");
        /**
         * 录入之前先根据用户输入的手机号码查询用户，看该手机号码是否已经注册过用户
         */
        Message msg = new Message();
        User byUserPhone = userService.findByUserPhone(userPhone);
        if(byUserPhone!=null){
            msg.setStatus(-1);
            msg.setResult("很遗憾，该手机号已被注册");
        }else{ // 准备录入用户
            User user = new User(userName,userPhone,idCard,userPwd);
            boolean flag = userService.insert(user);

            if(flag){
                //录入成功
                msg.setStatus(0);
                msg.setResult("用户录入成功!");
            }else{
                //录入失败
                msg.setStatus(-1);
                msg.setResult("用户录入失败!");
            }
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @RequestMapping("/find.do")
    public String find(HttpServletRequest request,HttpServletResponse response){
        String userPhone = request.getParameter("userPhone");
        User user = userService.findByUserPhone(userPhone);
        Message msg = new Message();
        if(user == null){
            msg.setStatus(-1);
            msg.setResult("不存在该手机号注册的用户信息!");
        }else{
            msg.setStatus(0);
            msg.setResult("查询成功!");
            msg.setData(user);
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    @RequestMapping("/update.do")
    public String update(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userPhone");
        String idCard = request.getParameter("idCard");
        String userPwd = request.getParameter("userPwd");

        /**
         * 更新之前先根据用户输入的手机号码查询用户，看该手机号码是否已经注册过用户
         */
        Message msg = new Message();
        User byUserPhone = userService.findByUserPhone(userPhone);
        System.out.println(byUserPhone);
        if(byUserPhone!=null){
            msg.setStatus(-1);
            msg.setResult("很遗憾，该手机号已被注册");
        }else{ // 准备更新用户信息
            User newUser = new User();
            newUser.setId(id);
            newUser.setUsername(userName);
            newUser.setUserphone(userPhone);
            newUser.setIdcard(idCard);
            newUser.setUserpwd(userPwd);

            boolean flag = userService.update(id, newUser);
            if(flag){
                msg.setStatus(0);
                msg.setResult("修改成功!");
            }else{
                msg.setStatus(-1);
                msg.setResult("修改失败!");
            }
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }


    @RequestMapping("/delete.do")
    public String delete(HttpServletRequest request,HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = userService.delete(id);
        Message msg = new Message();
        if(flag){
            msg.setStatus(0);
            msg.setResult("删除成功!");
        }else{
            msg.setStatus(-1);
            msg.setResult("删除失败!");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }


}
