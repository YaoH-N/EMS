package com.nyh.interceptor;

import com.nyh.utils.UserUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制访问权限的拦截器
 *  没有登录，不可以访问服务器中的资源，只有登录了才可以访问
 */
public class AccessInterceptor implements HandlerInterceptor {

    //执行时间：控制器方法执行之前，在ModelAndView返回之前
    //使用场景：登录验证
    //返回值：true ：继续执行控制器的方法  表示放行; false ：不会继续执行控制器方法 ，表示拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userName = (String)request.getSession().getAttribute("username");
        System.out.println(userName);
        if(userName != null){
            return true;
        }else{
//            request.getRequestDispatcher("/admin/login.html").forward(request,response);
//            response.sendRedirect("/admin/login.html");
            response.sendError(404,"很遗憾，权限不足，请先登录！");
            return false;
        }
    }

    //执行时间：控制器方法执行之后，在ModelAndView返回之前，有机会修改返回值
    //使用场景：日志的记录，记录登录的ip，时间
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //执行时间：控制器方法执行之后，在ModelAndView返回之后，没有机会修改返回值
    //使用场景：全局资源的一些操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
