package cn.news.filter;

import cn.news.entity.User;
import cn.news.vo.LoginUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LCB
 * @date 2022/7/6 16:41
 */

@WebFilter(urlPatterns = {"/user/*","/admin/*"})
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("会话权限过滤开启成功。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取请求对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println(request.getServletPath()+","+request.getContextPath());
        System.out.println(request.getServletPath().startsWith("/admin"));
        // 获取响应对象
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 判断登录状态
        HttpSession session = request.getSession();
        System.out.println("进入会话拦截");
        if(session.getAttribute("loginUser") == null){// 未登录
            response.sendRedirect("/index.jsp");// 回到登录页面
        }else{
            LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
            if(loginUser.getUrole() == 2 && request.getServletPath().indexOf("admin") != -1){//管理员
                filterChain.doFilter(request,response);// 放行
            }else if(loginUser.getUrole() == 1 &&  !(request.getServletPath().indexOf("admin") != -1)){// 普通用户
                filterChain.doFilter(request,response);// 放行
            }else{
                PrintWriter out = response.getWriter();
                response.setContentType("text/html;charset=utf-8");
                out.println("<h1>用户权限失效，请联系管理员。。。<a href='/index.jsp'>重新登录</a></h1>");
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("会话权限过滤销毁。。。");
    }
}
