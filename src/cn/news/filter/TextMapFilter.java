package cn.news.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author LCB
 * @date 2022/7/7 11:52
 */
@WebFilter(urlPatterns = {"/comments.do","/txt.do"})
public class TextMapFilter implements Filter {
    private HashMap<String,String> textMap = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext application = filterConfig.getServletContext();
        textMap = (HashMap<String, String>)application.getAttribute("textMap");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取请求对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ccontent = request.getParameter("ccontent");
        if (ccontent != null){

        }
        // 获取响应对象
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 放行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
