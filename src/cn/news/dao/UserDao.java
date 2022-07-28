package cn.news.dao;


import cn.news.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/27 19:01
 */
public interface UserDao {
    /**
     * 通过账号和密码查询用户
     * @param uname
     * @param upwd
     * @return
     */
    public User getUsers(String uname,String upwd) throws SQLException;


}
