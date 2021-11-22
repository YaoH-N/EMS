package com.nyh.controller;

import com.nyh.pojo.Message;
import com.nyh.service.AdminService;
import com.nyh.service.CourierService;
import com.nyh.service.ExpressService;
import com.nyh.service.UserService;
import com.nyh.utils.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "userServiceImpl") // 通过spring ioc 容器自动注入 UserService 对象
    private UserService userService;

    @Resource(name = "expressServiceImpl") // 自动注入
    private ExpressService expressService;
    @Resource(name = "courierServiceImpl") // 自动注入
    private CourierService courierService;


    /**
     * 登录请求
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("请求成功！login");

        //1.        接参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.        调用Service传参数 ， 并获取结果
        boolean result = adminService.login(username, password);
        //3.        根据结果，返回不同的数据给ajax,准备不同的数据
        Message msg = null;
        if (result) {
            msg = new Message(0, "登录成功"); // {status:0,result:"登录成功"}
            // 登录时间和ip的更新
            Date date = new Date();
            String ip = request.getRemoteAddr();    // 获取对方的地址
            adminService.updateLoginTimeAndIP(username, date, ip); // 更新登录时间和ip
            request.getSession().setAttribute("username", username);
            request.getSession().setMaxInactiveInterval(900); // 设置session的有效时间为15分钟
        } else {
            msg = new Message(-1, "登录失败");// {status:-1,result:"登录失败"}
        }
        //4.        将数据转换为JSON
        String json = JSONUtil.toJSON(msg);
        //5.        将JSON回复给ajax
        return json;
    }

    /**
     * 控制台请求，用来处理控制台界面的数据查询
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping("/console.do")
    public String console(HttpServletRequest req, HttpServletResponse resp) {
        List<Map<String, Integer>> data = expressService.console();
        data.add(courierService.console());
        data.add(userService.console());
        Message msg = new Message();
        if (data.size() == 0){  // 集合data大小=0，说明没有元素，快递数量查询失败
            msg.setStatus(-1); // 查询失败，设置响应信息状态为-1
        }else{
            msg.setStatus(0); // 查询成功，设置响应信息状态为0
        }
        msg.setData(data);
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 退出登录，清空session
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return "/admin/login";
    }


}
