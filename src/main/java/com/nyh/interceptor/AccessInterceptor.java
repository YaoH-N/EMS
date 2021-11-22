//package com.nyh.interceptor;
//
//import com.nyh.utils.UserUtil;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 控制访问权限的拦截器
// * 没有登录，不可以访问服务器中的资源，只有登录了才可以访问
// */
//public class AccessInterceptor implements HandlerInterceptor {
//
//    //执行时间：控制器方法执行之前，在ModelAndView返回之前
//    //使用场景：登录验证
//    //返回值：true ：继续执行控制器的方法  表示放行; false ：不会继续执行控制器方法 ，表示拦截
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String userName = (String) request.getSession().getAttribute("username");
//        String userPhone = (String) request.getSession().getAttribute("userPhone");
//        String uri = request.getRequestURI();
//        // 判断当前请求是否为登录或者注册请求，
//        if (uri.contains("login") || uri.contains("register")) {
//            // 如果是直接放行
//            return true;
//        }
//        // 网页后台登录session中会存储用户名，手机微信端登录会存储用户手机号码
//        if (uri.contains("admin")) {
//            // 如果是网页端后台请求，则判断session中是否有用户名来确定用户是否登录
//            if (userName != null) {
//                return true;
//            } else {
//                // 如果是网页端后台的请求，则重定向到网页端后台登录界面
//                response.sendRedirect("/admin/login.html");
//            }
//        } else if (uri.contains("wx")) {
//            //如果是微信端的请求，则判断session中是否有用户名来确定用户是否登录
//            if(userPhone != null){
//                return true;
//            }else{
//                // 如果是微信端的请求，则重定向到微信端登录页面
//                response.sendRedirect("/wx/login.html");
//            }
//
//        }
//        return false;
//    }
//
//    //执行时间：控制器方法执行之后，在ModelAndView返回之前，有机会修改返回值
//    //使用场景：日志的记录，记录登录的ip，时间
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    //执行时间：控制器方法执行之后，在ModelAndView返回之后，没有机会修改返回值
//    //使用场景：全局资源的一些操作
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
package com.nyh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  控制访问权限的拦截器
 *  没有登录，不可以访问服务器中的资源，只有登录了才可以访问
 */
public class AccessInterceptor implements HandlerInterceptor {

    //执行时间：控制器方法执行之前，在ModelAndView返回之前
    //使用场景：登录验证
    //返回值：true ：继续执行控制器的方法  表示放行; false ：不会继续执行控制器方法 ，表示拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userName = (String)request.getSession().getAttribute("username");
        String userPhone = (String)request.getSession().getAttribute("userPhone");
        String uri = request.getRequestURI();
        // 判断当前请求是否为登录或者注册请求，
        if(uri.contains("login")||uri.contains("register")){
            // 如果是直接放行
            return true;
        }
        // 网页后台登录session中会存储用户名，手机微信端登录会存储用户手机号码
        if(userName != null || userPhone != null){
            return true;
        }else{
            if(uri.contains("admin")){
                // 如果是网页端后台的请求，则重定向到网页端后台登录界面
                response.sendRedirect("/admin/login.html");
            }else if(uri.contains("wx")){
                // 如果是微信端的请求，则重定向到微信端登录页面
                response.sendRedirect("/wx/login.html");
            }
            return false;
        }
    }
}
