package cn.news.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LCB
 * @date 2022/6/27 15:01
 */
@WebServlet("/test.do")
public class TestServlet {
    public TestServlet() {
    }

    protected void doGet(HttpServletRequest rep, HttpServletResponse resp)throws IOException {
        System.out.println("Servlet后台get方法");
    }

    protected void doPost(HttpServletRequest rep, HttpServletResponse resp)throws IOException {
        System.out.println("Servlet后台post方法");
    }
}
