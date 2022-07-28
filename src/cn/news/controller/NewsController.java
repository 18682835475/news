package cn.news.controller;

import cn.news.entity.News;
import cn.news.entity.Topic;
import cn.news.sevrice.NewsService;
import cn.news.sevrice.TopicService;
import cn.news.sevrice.lmpl.NewsServicelmpl;
import cn.news.sevrice.lmpl.TopicServicelmpl;
import cn.news.utils.FilePathUtils;
import cn.news.utils.Page;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LCB
 * @date 2022/6/29 15:23
 */
@WebServlet("/news.do")
public class NewsController extends HttpServlet {
    private NewsService newsService = new NewsServicelmpl();
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
                case "add":{
                    add(req, resp);
                    break;
                }
                case "getNewsByList":{
                    getNewsByList(req, resp);
                    break;
                }
                case "getNewsByPage":{
                    getNewsByPage(req, resp);
                    break;
                }
                case "getNewsByNtidTop":{
                    getNewsByNtidTop(req, resp);
                    break;
                }
                case "getIndexNews":{
                    getIndexNews(req, resp);
                    break;
                }
                case "getNewsByNtidPage":{
                    getNewsByNtidPage(req,resp);
                    break;
                }
                case "toModify":{
                    toModify(req,resp);
                    break;
                }
                case "readNews":{
                    readNews(req,resp);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readNews(HttpServletRequest req,HttpServletResponse resp) throws Exception{

    }

    public void toModify(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        // 得到请求的新闻id
        Integer nid = Integer.parseInt(req.getParameter("nid"));
        // 查询该新闻id的详情
        News news = newsService.getNewsByNid(nid);
        String path = FilePathUtils.makePath(news.getNpicPath(),"\\upload")+"/"+news.getNpicPath();
        news.setNpicPath(path);
        req.setAttribute("news",news);// 请求赋值
        // 查询所有主题
        List<Topic> topics = topicService.getAll();
        req.setAttribute("topics",topics);// 请求赋值
        //转发
        req.getRequestDispatcher("/admin/news_modify.jsp").forward(req,resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();
        // 封装News参数
        News news = new News();
        /*news.setNtid(Integer.valueOf(req.getParameter("ntid")));
        news.setNtitle(req.getParameter("ntitle"));
        news.setNauthor(req.getParameter("nauthor"));
        news.setNcontent(req.getParameter("ncontent"));
        news.setNsummary(req.getParameter("nsummary"));
        news.setNpicpath(req.getParameter("file"));*/
        //1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //3、判断提交上来的数据是否是上传表单的数据
        if(!ServletFileUpload.isMultipartContent(req)){
            //按照传统方式获取数据
            return;
        }
        //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        List<FileItem> list = upload.parseRequest(req);
        //6.加密	  //5.循环取数据
        for(FileItem file:list) {
            //判断表单数据
            if (file.isFormField()) {//普通表单数据
                //file.getFieldName();//普通表单数据的name
                //file.getString("UTF-8");//普通表单数据的value
                if("ntid".equals(file.getFieldName())){
                    news.setNtid(Integer.valueOf(file.getString("UTF-8")));
                }
                if("ntitle".equals(file.getFieldName())){
                    news.setNtitle(file.getString("UTF-8"));
                }
                if("nauthor".equals(file.getFieldName())){
                    news.setNauthor(file.getString("UTF-8"));
                }
                if("nsummary".equals(file.getFieldName())){
                    news.setNsummary(file.getString("UTF-8"));
                }
                if("ncontent".equals(file.getFieldName())){
                    news.setNcontent(file.getString("UTF-8"));
                }
            } else {//文件数据
                String saveName = FilePathUtils.makeFileName(file.getName());
                news.setNpicPath(saveName);
                //file.write(new File(req.getSession().getServletContext().getRealPath("upload/")+ file.getName()));//保存文件到指定位置
                String savePath = FilePathUtils.makePath(saveName,"D:\\upload");
                file.write(new File(savePath+"\\" + saveName));//保存文件到指定位置
            }
        }
        System.out.println(JSONObject.toJSONString(news));
        if(newsService.add(news)){
            out.println(JSONObject.toJSONString(ResultCode.success()));
        }else{
            out.println(JSONObject.toJSONString(ResultCode.failed()));
        }
        out.close();
    }

    public void getNewsByNtidPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        PrintWriter out = resp.getWriter();
        // 分页查询某主题下的新闻
        long ntid = req.getParameter("ntid") == null ? 1 : Long.parseLong(req.getParameter("ntid"));
        long pageNumber = req.getParameter("pageNumber") == null ? 1 : Long.parseLong(req.getParameter("pageNumber"));
        long pageSize = req.getParameter("pageSize") == null ? 10 : Long.parseLong(req.getParameter("pageSize"));
        Page<News> newsPage = newsService.getNewsByNtidPage(ntid,pageNumber,pageSize);
        out.println(JSONObject.toJSONString(ResultCode.success(newsPage)));
        out.close();
    }

    /**
     * 查询新闻前台首页的数据
     * @param req
     * @param resp
     * @throws IOException
     * @throws SQLException
     */
    //localhost:8080/news.do?opr=getIndexNews&ntid=1&pageNumber=1&pageSize=5
    public void getIndexNews(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        PrintWriter out = resp.getWriter();
        // 查询所有主题
        List<Topic> topicList = topicService.getAll();
        // 分页查询某主题下的新闻
        long ntid = req.getParameter("ntid") == null ? 1 : Long.parseLong(req.getParameter("ntid"));
        long pageNumber = req.getParameter("pageNumber") == null ? 1 : Long.parseLong(req.getParameter("pageNumber"));
        long pageSize = req.getParameter("pageSize") == null ? 10 : Long.parseLong(req.getParameter("pageSize"));
        Page<News> newsPage = newsService.getNewsByNtidPage(ntid,pageNumber,pageSize);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("topicList",topicList);
        map.put("newsPage",newsPage);
        out.println(JSONObject.toJSONString(ResultCode.success(map)));
        out.close();
    }

    // localhost:8080/news.do?opr=getNewsByNtidTop&ntid=1&pageSize=5
    public void getNewsByNtidTop(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        PrintWriter out = resp.getWriter();
        long ntid = Long.parseLong(req.getParameter("ntid"));
        long pageSize = Long.parseLong(req.getParameter("pageSize"));
        out.println(JSONObject.toJSONString(ResultCode.success(newsService.getNewsByNtidTop(ntid,pageSize))));
        out.close();
    }

    // localhost:8080/news.do?opr=getNewsByPage&pageNumber=1&pageSize=20
    public void getNewsByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        // 获得请求的页面信息
        long pageNumber = req.getParameter("pageNumber") == null ? 1 : Long.parseLong(req.getParameter("pageNumber"));
        long pageSize = req.getParameter("pageSize") == null ? 10 : Long.parseLong(req.getParameter("pageSize"));
        out.println(JSONObject.toJSONString(ResultCode.success(newsService.getNewsByPage(pageNumber,pageSize))));
        out.close();
    }

    public void getNewsByList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println(JSONObject.toJSONString(ResultCode.success(newsService.getNewsByList())));
        out.close();
    }
}
