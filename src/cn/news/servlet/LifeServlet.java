package cn.news.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet 步骤
 *  1.extends 继承HttpServlet
 *  2.重写 doGet或doPost
 *  3.配置该servlet对应请求路径 @WebServlet或web.xml
 *  servlet生命周期
 *  1.被客户端请求之后，再创建
 *  2.调用init方法，获取初始化参数
 *  3。每次用户进入service方法，根据客户端请求的方式进行不同的do方法
 *  4.web应用关闭时，调用destroy方法销毁
 *
 * @author LCB
 * @date 2022/6/27 15:41
 */
@WebServlet("/life.do")
public class LifeServlet extends HttpServlet {
    public LifeServlet(){
        System.out.println("调用构造函数,创建servlet对象");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("调用init(),加载初始数据");
    }

    @Override
    public void destroy() {
        System.out.println("调用destroy,销毁该对象。。");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用service方法，进入doxxx方法的调用");
        super.service(req,resp);
    }
}
