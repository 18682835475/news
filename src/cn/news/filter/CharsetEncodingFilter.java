package cn.news.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * 过滤器的作用: 通过路径的拦截，对用户的请求和响应做出处理
 * 过滤器的步骤:
 *  1、实现接口javax.servlet.Filter
 *  2、实现方法:
 *      init() 过滤器初始化方法,加载配置信息
 *      destrou() 过滤器销毁方法，释放资源
 *      doFilter() 日志、权限、异常、编码同一、敏感过滤
 *  3、配置过滤的请求路径
 * @author LCB
 * @date 2022/7/6 15:53
 */
@WebFilter(value = "/*",initParams = {
        @WebInitParam(name = "CHARSET",value = "UTF-8")
})
public class CharsetEncodingFilter implements Filter {
    private String charset;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        charset = filterConfig.getInitParameter("CHARSET");
        System.out.println(charset+"字符编码过滤器启动...");;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取请求对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding(charset);
        if (request.getServletPath().endsWith(".do")){
            System.out.println("请求路径:"+request.getServletPath()+","+request.getContextPath());
        }
        // 获取响应对象
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 过滤器 放行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("字符编码过滤器销毁...");;
    }
}
