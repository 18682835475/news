package cn.news.controller;

import cn.news.entity.Comments;
import cn.news.entity.News;
import cn.news.sevrice.CommentsService;
import cn.news.sevrice.NewsService;
import cn.news.sevrice.lmpl.CommentsServicelmpl;
import cn.news.sevrice.lmpl.NewsServicelmpl;
import cn.news.utils.FilePathUtils;
import cn.news.utils.ResultCode;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/29 15:23
 */
@WebServlet("/comments.do")
public class CommentsController extends HttpServlet {
    private CommentsService commentsService = new CommentsServicelmpl();
    private NewsService newsService = new NewsServicelmpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1.设置请求编码
//            req.setCharacterEncoding("UTF-8");
            // 2.设置响应格式
            resp.setContentType("text/html;charset=utf-8");
            // 3.请求参数路由
            String opr = req.getParameter("opr");
            switch (opr) {
                case"addComments":{
                    addComments(req,resp);
                    break;
                }
                case "getCommentsByCnidTop":{
                    getCommentsByCnidTop(req,resp);
                    break;
                }
                case "getCommentsByPage":{
                    getCommentsByPage(req,resp);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addComments(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 评论的信息
        Integer cnid = Integer.valueOf(req.getParameter("cnid"));
        String ccontent = req.getParameter("ccontent");
        String cip = req.getParameter("cip");
        String cauthor = req.getParameter("cauthor");
        // 实体类
        Comments comments = new Comments();
        comments.setCnid(cnid);
        comments.setCauthor(cauthor);
        comments.setCcontent(ccontent);
        comments.setCip(cip);
        // 新增

    }
    public void getCommentsByCnidTop(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        PrintWriter out = resp.getWriter();
        long cnid = Long.parseLong(req.getParameter("cnid"));
        long pageSize = Long.parseLong(req.getParameter("pageSize"));
        out.println(JSONObject.toJSONString(ResultCode.success(newsService.getNewsByNtidTop(cnid,pageSize))));
        out.close();
    }

    public void getCommentsByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        // 获得请求的页面信息
        long pageNumber = req.getParameter("pageNumber") == null ? 1 : Long.parseLong(req.getParameter("pageNumber"));
        long pageSize = req.getParameter("pageSize") == null ? 10 : Long.parseLong(req.getParameter("pageSize"));
        out.println(JSONObject.toJSONString(ResultCode.success(newsService.getNewsByPage(pageNumber,pageSize))));
        out.close();
    }
}
