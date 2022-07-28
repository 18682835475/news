package cn.news.controller;

import cn.news.entity.Topic;
import cn.news.entity.User;
import cn.news.sevrice.TopicService;
import cn.news.sevrice.UsersService;
import cn.news.sevrice.lmpl.TopicServicelmpl;
import cn.news.sevrice.lmpl.UsersServiceImpl;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LCB
 * @date 2022/6/29 15:18
 */
@WebServlet("/topic.do")
public class TopicController extends HttpServlet {
    private TopicService topicService = new TopicServicelmpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            // 1.设置请求编码
//            req.setCharacterEncoding("UTF-8");
            // 2.设置响应格式
            resp.setContentType("text/html;charset=utf-8");
            // 3.请求参数路由
            String opr = req.getParameter("opr");
            // 4.路由分配
            switch (opr){
                case "getAll":{
                    getAll(req, resp);
                    break;
                }
                case "getTopicByName":{
                    getTopicByName(req, resp);
                    break;
                }
                case "add":{
                    add(req, resp);
                    break;
                }
                case "update":{
                    update(req, resp);
                    break;
                }
                case "delete":{
                    delete(req,resp);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json;charset=utf-8");
        // 获取参数
        Integer tid = Integer.valueOf(req.getParameter("tid"));
        Topic topic = new Topic();
        topic.setTid(tid);
        out.write(JSONObject.toJSONString(topicService.delete(tid)));
        out.close();
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json;charset=utf-8");
        // 获取参数
        String tname = req.getParameter("tname");
        Integer tid = Integer.valueOf(req.getParameter("tid"));
        Topic topic = new Topic();
        topic.setTname(tname);
        topic.setTid(tid);
        out.write(JSONObject.toJSONString(topicService.update(topic)));
        out.close();
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json;charset=utf-8");
        // 获取参数
        String tname = req.getParameter("tname");
        Topic topic = new Topic();
        topic.setTname(tname);
        out.write(JSONObject.toJSONString(topicService.add(topic)));
        out.close();
    }
    public void getTopicByName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json;charset=utf-8");
        // 获取参数
        String tname = req.getParameter("tname");
        Boolean flag = false;
        if(topicService.getTopicByName(tname) != null){// 主题名称已存在
            flag = true;
        }
        out.write(JSONObject.toJSONString(flag));
        out.close();
    }
    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json;charset=utf-8");
        out.write(JSONObject.toJSONString(topicService.getAll()));
        out.close();
    }
}
