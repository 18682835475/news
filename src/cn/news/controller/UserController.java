package cn.news.controller;


import cn.news.entity.News;
import cn.news.entity.User;
import cn.news.sevrice.UsersService;
import cn.news.sevrice.lmpl.UsersServiceImpl;
import cn.news.vo.LoginUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LCB
 * @date 2022/6/29 13:35
 */
@WebServlet("/users.do")
public class UserController extends HttpServlet {
    private UsersService usersService = new UsersServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.设置请求编码
        /*req.setCharacterEncoding("UTF-8");*/
        // 2.设置响应格式
        resp.setContentType("text/html;charset=utf-8");
        // 3.请求参数路由
        String opr = req.getParameter("opr");
        // 4.路由分配
        switch (opr){
            case "login":{// http://localhost:8080/users.do?opr=login
                login(req, resp);
                break;
            }
            case "register":{// http://localhost:8080/users.do?opr=register
                register(req, resp);
                break;
            }
            case "loginOut":{
                req.getSession().invalidate();// 清空会话
                resp.sendRedirect("/index.jsp");
                break;
            }
        }
    }

    public void register(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("register");
    }
    // http://localhost:8080/users.do?opr=login&uname=admin&upwd=admin
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 接受账号和密码
        String uname = req.getParameter("uname");
        String upwd = req.getParameter("upwd");
        // 调用登录方法
        User users = usersService.login(uname,upwd);
        // 响应内容
        PrintWriter out = resp.getWriter();
        if(users == null){// 登录失败
            // 转发 一次请求
            req.setAttribute("loginError","账号或密码不匹配！");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }else{// 登录成功
            // session会话，记录当前用户的信息
            HttpSession session = req.getSession();
            // 重定向，客户端发起第二次请求
            session.setMaxInactiveInterval(10);// 用户活跃时间
            /*session.setAttribute("username",users.getUname());*/
            // 存放用户LoginUser
            LoginUser loginUser = new LoginUser();
            loginUser.setUid(users.getUid());
            loginUser.setUname(users.getUname());
            loginUser.setUrole(users.getUrole());
            loginUser.setUroleName(users.getUrole() == 1 ? "普通用户":"管理员");
            loginUser.setIp(req.getLocalAddr());// IP
            session.setAttribute("loginUser",loginUser);
            // 身份判断
            if(users.getUrole() == 1){// 普通用户 新闻首页
                // 存放cookie数据
                if("1".equals(req.getParameter("reName"))){//记住密码
                    System.out.println("记住密码");
                    // 创建cookie
                    Cookie nameCookie = new Cookie("loginUsername", users.getUname());
                    Cookie pwdCookie = new Cookie("loginUserpwd", users.getUpwd());
                    // 设置有效期
                    nameCookie.setMaxAge(60*60*24*7);
                    pwdCookie.setMaxAge(60*60*24*7);
                    // 设置访问路径
                    nameCookie.setPath("/");
                    pwdCookie.setPath("/");
                    // 存放到客户端
                    resp.addCookie(nameCookie);
                    resp.addCookie(pwdCookie);
                }
                resp.sendRedirect("/index.jsp");
            }else if(users.getUrole() == 2){// 超管 后台管理页面
                session.setMaxInactiveInterval(60*15);// 用户活跃时间
                resp.sendRedirect("/admin/admin.jsp");
            }
        }
        // 关闭流
        out.close();
    }
}
