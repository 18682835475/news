package cn.news.dao.lmpl;

import cn.news.dao.UserDao;
import cn.news.entity.BaseDao;
import cn.news.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/29 11:23
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    /**
     * 通过账号和密码查询用户
     *
     * @param uname
     * @param upwd
     * @return
     */
    @Override
    public User getUsers(String uname, String upwd) throws SQLException {
        User users = null;
        ResultSet resultSet = null;
        try{
            String sql = "select * from news_users where uname = ? and upwd = ?;";
            Object[] params = {uname,upwd};
            resultSet = this.executeQuery(sql,params);
            while(resultSet.next()){
                users = new User();
                users.setUid(resultSet.getInt("uid"));
                users.setUname(resultSet.getString("uname"));
                users.setUpwd(resultSet.getString("upwd"));
                users.setUrole(resultSet.getInt("urole"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return users;
    }
}
