package cn.news.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LCB
 * @date 2022/6/27 16:05
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login.do");
        // 1.设置请求编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        // 2.接收数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+","+password);
        //3.模拟业务层
        if ("admin".equals(username) && "admin".equals(password)){ // 登录成功
            out.println("<h1>主页<h1>");
            out.println("<p>欢迎:"+username+"<p>");
        } else { //登录失败
            out.println("<h1>登录失败!账号或密码不匹配!<h1>");
            out.println("<a href='/login.html'>回到登录页</a>");
        }
        out.close();
    }
}
