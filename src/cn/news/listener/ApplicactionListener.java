package cn.news.listener;

import cn.news.sevrice.TopicService;
import cn.news.sevrice.lmpl.TopicServicelmpl;
import cn.news.vo.LoginUser;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 监听器 : 当对象触发某个事件时,触发代码块
 * 监听器的监听对象: request,session,application
 * 监听器的类型:
 *      1、状态监听(初始化和销毁)
 *      2、属性监听(属性增加,修改、删除)
 *      3、感知型监听器(绑定和钝化session)
 * 监听器的实现步骤:
 *
 * @author LCB
 * @date 2022/7/6 17:06
 */
@WebListener
public class ApplicactionListener implements ServletContextListener, ServletContextAttributeListener {
    private TopicService topicService = new TopicServicelmpl();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 获得上下文
        ServletContext application = sce.getServletContext();
        // 初始化主题
        try {
            application.setAttribute("topicList",topicService.getAll());
            System.out.println("新闻主题加载成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 初始化系统参数
        try {
            application.setAttribute("appname","四川传媒");
            System.out.println("系统参数加载成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 初始化在线用户列表
        try{
            Map<String, LoginUser> map = new HashMap<String,LoginUser>();
            application.setAttribute("onlineUsers",map);
            System.out.println("在线用户人数：" + map.size());
        }catch (Exception e) {
            e.printStackTrace();
        }
        // 初始化敏感字过滤集合
        try{
            Map<String,String> textMap = new HashMap<String,String>();
            textMap.put("你我他","***");
            textMap.put("<","&lt;");
            textMap.put(">","&gt;");
            application.setAttribute("textMap",textMap);
            System.out.println("敏感词过滤初始化过程。。。");
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("上下文初始化完成。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("上下文初始化销毁。。。");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("上下文中新增了属性。。。"+scae.getName()+"="+scae.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("上下文中删除了属性。。。");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("上下文中修改了属性。。。");
    }


}
