package cn.news.entity;

import java.io.Serializable;

/**
 * @author LCB
 * @date 2022/6/27 18:47
 */
public class User implements Serializable {
    private Integer uid;
    private String uname;
    private String upwd;
    private Integer urole;

    public User() {
    }

    public User(Integer uid, String uname, String upwd, Integer urole) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.urole = urole;
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

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public Integer getUrole() {
        return urole;
    }

    public void setUrole(Integer urole) {
        this.urole = urole;
    }
}
