package com.nyh.wx.controller;

import com.nyh.pojo.Message;
import com.nyh.utils.JSONUtil;
import com.nyh.utils.UserUtil;
import com.nyh.wx.util.SignatureUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@ResponseBody
@RequestMapping("/wx/qrcode")
public class QRCodeController {

    /**
     * 生成二维码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/createQRCode.do")
    public void createQRCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求参数
        String type = request.getParameter("type");
        String code = null;
        String userPhone = null;
        String QRCodeContent = null;
        if ("express".equals(type)) {
            // 表示要展示的是快递的二维码，展示单个快递的信息
            code = request.getParameter("code");
            QRCodeContent = "express_".concat(code);
        } else {
            // 用户的二维码：被扫后，快递员端展示用户的所有快递
            // 这里要先获取微信用户，再获取其电话号码，然后根据电话号码查询所有快递
            userPhone = UserUtil.getWxUser(request.getSession()).getUserphone();
            QRCodeContent = "userPhone_".concat(userPhone);
        }
        HttpSession session = request.getSession();
        session.setAttribute("qrcode", QRCodeContent);
        // 返回ModelAndView视图，而不是返回字符串
        response.sendRedirect("/wx/personQRcode.html");
    }

    /**
     * 获取二维码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/qrcode.do")
    public String getQRCode(HttpServletRequest request, HttpServletResponse response) {
        String qrcode = (String) request.getSession().getAttribute("qrcode");
        Message msg = new Message();
        if (qrcode == null) {
            msg.setStatus(-1);
            msg.setResult("取件码获取出错，请用户重新操作");
        } else {
            msg.setStatus(0);
            msg.setResult(qrcode);
        }
        return JSONUtil.toJSON(msg);
    }

    /**
     * 调起微信扫一扫
     */
    @RequestMapping("/wxconf.do")
    protected void wxConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String urlText = request.getParameter("xurl");
        try {
            String json = SignatureUtil.getConfig(urlText).toJSON();
            pw.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.close();
    }
}
