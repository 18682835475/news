package cn.news.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LCB
 * @date 2022/7/4 11:55
 */
public class UserTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从请求参数中取得用户名
        String userName = req.getParameter("userName");
        // 密码
        String password = req.getParameter("password");
        // 业余爱好
        String[] habits = req.getParameterValues("habits");
        // 此处生成一个user对象，user为自定义实例类，用于封装用户信息
        UserTest userTest = new UserTest();
        userTest.setName(userName);
        userTest.setPassword(password);
        userTest.setHabits(java.util.Arrays.asList(habits)); // 转化为List<String>

        // 把此user对象设置为rep作用域内的一个变量
        req.setAttribute("userObj",userTest);
        req.getRequestDispatcher("/regSuccess.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        this.doGet(req,resp);
    }
}
