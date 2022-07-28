package cn.news.vo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 领域模型
 * 会话存储的实体类
 * @author LCB
 * @date 2022/7/7 9:11
 */
public class LoginUser implements Serializable, HttpSessionBindingListener, HttpSessionActivationListener {
    private Integer uid;
    private String uname;
    private Integer urole;
    private String uroleName;  // 角色名称
    private String type;  // 登录方式
    private String client; // 客户端
    private String ip; // IP地址

    public LoginUser() {
    }

    public LoginUser(Integer uid, String uname, Integer urole, String uroleName, String type, String client, String ip) {
        this.uid = uid;
        this.uname = uname;
        this.urole = urole;
        this.uroleName = uroleName;
        this.type = type;
        this.client = client;
        this.ip = ip;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getUrole() {
        return urole;
    }

    public void setUrole(Integer urole) {
        this.urole = urole;
    }

    public String getUroleName() {
        return uroleName;
    }

    public void setUroleName(String uroleName) {
        this.uroleName = uroleName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 当前实体类 被存储到session时触发该方法
     * @param event
     */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println(this.getUname()+"用户上线了。。。");
        ServletContext application =  event.getSession().getServletContext();
        if(application.getAttribute("onlineUsers") != null){
            HashMap<String,LoginUser> map = (HashMap<String,LoginUser>)application.getAttribute("onlineUsers");
            map.put(this.getUname(),this);
            System.out.println("在线人数：" +map.size());
        }
    }

    /**
     * 当前实体类 被移出session时触发该方法
     * @param event
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println(this.getUname()+"下线了。。。");
        ServletContext application =  event.getSession().getServletContext();
        if(application.getAttribute("onlineUsers") != null){
            HashMap<String,LoginUser> map = (HashMap<String,LoginUser>)application.getAttribute("onlineUsers");
            map.remove(this.getUname());
            System.out.println("在线人数：" +map.size());
        }
    }

    /**
     * 活化： 将持久化的数据加载到内存中（反序列化）
     * @param se
     */
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("会话活化");
    }

    /**
     * 钝化： 将session中的数据保存到持久化文件中（序列化）
     * @param se
     */
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("会话钝化");
    }
}
