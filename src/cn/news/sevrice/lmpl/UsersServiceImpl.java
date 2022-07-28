package cn.news.sevrice.lmpl;


import cn.news.dao.UserDao;
import cn.news.dao.lmpl.UserDaoImpl;
import cn.news.entity.User;
import cn.news.sevrice.UsersService;
import cn.news.utils.DataBaseUtils;

import java.sql.Connection;


/**
 * @author LCB
 * @date 2022/6/29 14:10
 */
public class UsersServiceImpl implements UsersService {
    /**
     * 用户登录
     *
     * @param uname
     * @param upwd
     * @return
     */
    @Override
    public User login(String uname, String upwd) throws RuntimeException{
        Connection connection = null;
        User users = null;
        try{
            connection = DataBaseUtils.getConnection();
            UserDao usersDao = new UserDaoImpl(connection);
            users = usersDao.getUsers(uname,upwd);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DataBaseUtils.closeAll(connection,null,null);
        }
        return users;
    }
}
