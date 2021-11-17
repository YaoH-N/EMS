package com.nyh.wx.controller;

import com.nyh.pojo.Courier;
import com.nyh.pojo.Express;
import com.nyh.pojo.Message;
import com.nyh.pojo.User;
import com.nyh.service.CourierService;
import com.nyh.service.ExpressService;
import com.nyh.service.UserService;
import com.nyh.utils.JSONUtil;
import com.nyh.utils.RandomUtil;
import com.nyh.utils.TencentSMSUtil;
import com.nyh.utils.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ResponseBody // 该注解声明下的类所有返回字符串的方法都不去查找跟字符串同名的视图，而是直接返回字符串本身
@RequestMapping("/wx/user")
public class WxUserController {

    @Resource
    private CourierService courierService;

    @Resource
    private UserService userService;

    @Resource
    private ExpressService expressService;

    /**
     * 登录发短信请求
     */
    @RequestMapping("/loginSms.do")
    public String sendSms(HttpServletRequest request, HttpServletResponse response) {
        String userPhone = request.getParameter("userPhone");
        String code = String.valueOf(RandomUtil.getCode());
        boolean flag = TencentSMSUtil.send(userPhone, code);// 向控制台发送验证码
        Message msg = new Message();
        if (flag) {
            msg.setStatus(0);
            msg.setData("您的验证码为："+code);
            msg.setResult("验证码已发送，请查收!");
        } else {
            msg.setStatus(-1);
            msg.setResult("验证码下发失败，请检查手机号或稍后再试!");
        }
        // 将手机号对应的验证码信息存入session中，用于登录判断
        UserUtil.setLoginSms(request.getSession(), userPhone, code);
        return JSONUtil.toJSON(msg);
    }

    @RequestMapping("/login.do")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        // 1、接收请求的参数
        String userPhone = request.getParameter("userPhone");
        String userCode = request.getParameter("code"); // 用户输入的验证码
        // 获取存储在session中的信息  key：输入的手机号，value：向该手机号发送的验证码
        String sysCode = UserUtil.getLoginSms(request.getSession(), userPhone);
        Message msg = new Message();
        if (sysCode == null) {
            // 验证码为空，该手机号未获取短信验证码
            msg.setStatus(-1);
            msg.setResult("该手机号码未获取短信!");
        } else if (sysCode.equals(userCode)) {   // 输入的验证码和session中存储的验证码相同
            User user = new User();
            user.setUserphone(userPhone);
            // 判断是用户登录还是快递员登录
            if (courierService.findByExPhone(userPhone) != null) {
                // 快递员登录（包含普通用户的权限）
                msg.setStatus(1);
                msg.setResult("登录成功!");
                user.setUser(false);// 这是新添加的属性，用于判断该手机号是用户还是快递员 false表示快递员
            } else if (userService.findByUserPhone(userPhone) != null) {
                // 普通用户登录
                msg.setStatus(0);
                msg.setResult("登录成功!");
                user.setUser(true);
            } else {
                // 用户不存在
                msg.setStatus(-3);
                msg.setResult("该用户不存在，正在前往注册页面");
                return JSONUtil.toJSON(msg);
            }

            UserUtil.setWxUser(request.getSession(), user);// 将手机号和验证码的对应信息存入session

        } else {
            // 验证码不一致，登录失败
            msg.setStatus(-2);
            msg.setResult("验证码不一致");
        }
        return JSONUtil.toJSON(msg);
    }

    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Message msg = new Message(0);
        return JSONUtil.toJSON(msg);
    }

    /**
     * 实现是否显示快递助手的业务逻辑
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/uerInfo.do")
    public String userInfo(HttpServletRequest request, HttpServletResponse response) {
        User user = UserUtil.getWxUser(request.getSession());
        Boolean isUser = user.getUser();
        Message msg = new Message();
        if (isUser) {
            msg.setStatus(0);
        } else {
            msg.setStatus(1);
        }
        msg.setResult(user.getUserphone());
        return JSONUtil.toJSON(msg);
    }

    /**
     * 用户注册请求
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/register.do")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        // 1. 获取请求参数
        String role = request.getParameter("role");
        String username = request.getParameter("username");
        String userphone = request.getParameter("userphone");
        String idcard = request.getParameter("idcard");
        String password = request.getParameter("password");
        System.out.println(role);
        Message msg = new Message();
        if ("1".equals(role)) {
            // 注册为用户
            msg.setStatus(1);
            userService.insert(new User(username, userphone, idcard, password));
            msg.setResult("用户注册成功!");
        } else if ("0".equals(role)) {
            // 注册为快递员
            msg.setStatus(0);
            courierService.insert(new Courier(username, userphone, idcard, password));
            msg.setResult("快递员注册成功!");
        } else {
            msg.setStatus(-1);
            msg.setResult("请您选择注册的用户类型!");
        }
        return JSONUtil.toJSON(msg);
    }

    @RequestMapping("/findExpressByNum.do")
    public String findExpressByNum(HttpServletRequest request, HttpServletResponse response) {
        // 获取登录时session中存储的user对象信息 为了下面判断该用户是快递员还是其他用户
        User user = UserUtil.getWxUser(request.getSession());
        // 为false表示当前用户为快递员，true表示为普通用户
        Boolean isUser = user.getUser();
        // 获取请求参数，快递单号
        String expressNum = request.getParameter("expressNum");
        Express express = expressService.findByNumber(expressNum);  // 根据快递单号查询快递信息
        Message msg = new Message();
        if (express == null) {
            msg.setStatus(-1);
            msg.setResult("快递单号不存在!");
            return JSONUtil.toJSON(msg);
        }
        // 封装查询结果文字信息，用来显示给用户
        String expressMsg = "姓名：" + express.getUsername() + "； " +
                "手机号：" + express.getUserphone() + "； " +
                "公司：" + express.getCompany() + "； " +
                "取件码：" + express.getCode() + "； " +
                "状态：" + (express.getStatus() == 0 ? "未出库" : "已出库");

        if (isUser) {
            /**
             * 用户查询：
             *  如果用户查询的快递信息不属于自己，则不显示快递信息
             *  如果查询的快递是自己的，则显示快递信息
             */
            // 为true，用户查询
            if (express.getUserphone().equals(user.getUserphone())) {
                // 快递是该用户的
                msg.setStatus(0);
                msg.setResult("查询成功!");
                msg.setData(expressMsg);
            } else {
                // 快递不是该用户的
                msg.setStatus(-2);
                msg.setResult("该快递不属于您!");
                msg.setData("该快递不属于您!");
            }
        } else {
            // 为false, 快递员查询,可以查询出所有快递信息
            msg.setStatus(0);
            msg.setResult("查询成功!");
            msg.setData(expressMsg);
        }
        return JSONUtil.toJSON(msg);
    }

    @RequestMapping("/getUsername.do")
    public String getUsername(HttpServletRequest request, HttpServletResponse response) {
        User wxUser = UserUtil.getWxUser(request.getSession());
        Message msg = new Message();
        if (null == wxUser.getUserphone()) {
            msg.setStatus(-1);
            msg.setData("--");
        } else {
            msg.setStatus(0);
            if (wxUser.getUsername() == null) {
                if (wxUser.getUser()) {
                    User user = userService.findByUserPhone(wxUser.getUserphone());
                    msg.setData(user.getUsername());
                } else {
                    Courier courier = courierService.findByExPhone(wxUser.getUserphone());
                    msg.setData(courier.getExname());
                }
            } else {
                msg.setData(wxUser.getUsername());
            }
        }
        return JSONUtil.toJSON(msg);
    }

    @RequestMapping("/updateInfoByPhone.do")
    public String updateInfoByPhone(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求参数
        String username = request.getParameter("username");
        // 新手机号码
        String userPhone = request.getParameter("userPhone");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        // 发送验证码
        String sysCode = UserUtil.getLoginSms(request.getSession(), userPhone);

        // 获取session中存储的用户对象信息
        User wxUser = UserUtil.getWxUser(request.getSession());
        Message msg = new Message();
        if (wxUser == null){
            msg.setResult("登录信息失效，请重新登录!");
            return JSONUtil.toJSON(msg);
        }
        if (sysCode == null) {
            // 该手机号未获取短信
            msg.setStatus(-1);
            msg.setResult("该手机号码未获取短信!");
        } else if (!sysCode.equals(code)) {
            msg.setStatus(-2);
            msg.setResult("验证码输入有误!");
        } else {
            // 获取isUser标志，判断是当前登录的用户是普通用户还是快递员 true：普通用户；false：快递员
            Boolean isUser = wxUser.getUser();
            boolean flag = false;
            if (isUser) {
                // 根据手机号查找用户
                User newUser = userService.findByUserPhone(wxUser.getUserphone());
                newUser.setUsername(username);// 修改用户名
                newUser.setUserphone(userPhone);// 修改手机号
                newUser.setUserpwd(password);
                newUser.setUser(true);
                // 根据id修改用户信息
                flag = userService.update(newUser.getId(), newUser);
                // 更新session中的用户信息
                UserUtil.setWxUser(request.getSession(), newUser);
            } else {
                Courier newCourier = courierService.findByExPhone(wxUser.getUserphone());
                newCourier.setExname(username);
                newCourier.setExphone(userPhone);
                newCourier.setExpassword(password);
                flag = courierService.update(newCourier.getId(), newCourier);

                User user = new User();
                user.setUsername(username);
                user.setUserphone(userPhone);
                user.setUser(false);
                UserUtil.setWxUser(request.getSession(), user);
            }

            if (flag) { // flag=true,表示信息修改成功
                msg.setStatus(0);
                msg.setResult("信息修改成功!");
            } else {
                msg.setStatus(-3);
                msg.setResult("信息修改失败!");
            }
        }
        return JSONUtil.toJSON(msg);
    }

    /**
     * 根据用户手机号查询用户信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/findByUserPhone.do")
    public String findByUserPhone(HttpServletRequest request, HttpServletResponse response) {
        // 获取session中存储的当前登录的用户对象信息
        User wxUser = UserUtil.getWxUser(request.getSession());
        Message msg = new Message();
        if (wxUser == null){
            msg.setStatus(-1);
            msg.setResult("登录信息失效，请重新登录!");
            return JSONUtil.toJSON(msg);
        }
        // 获取手机号码
        String userPhone = wxUser.getUserphone();

        System.out.println(wxUser);
        // 判断是用户登录还是快递员登录
        if (!wxUser.getUser()) {
            // 快递员登录（包含普通用户的权限） 根据手机号码查询快递员个人信息
            Courier byExPhone = courierService.findByExPhone(userPhone);
            msg.setStatus(1);
            msg.setResult("登录成功!");
            msg.setData(byExPhone);
        } else if (wxUser.getUser()) {
            // 普通用户登录,根据手机号码查询用户个人信息
            User byUserPhone = userService.findByUserPhone(userPhone);
            msg.setStatus(0);
            msg.setResult("登录成功!");
            msg.setData(byUserPhone);
        } else {
            // 用户不存在
            msg.setStatus(-1);
            msg.setResult("请先登录!");
        }

        // 返回json对象
        return JSONUtil.toJSON(msg);
    }

}
