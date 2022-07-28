package cn.news.sevrice;

import cn.news.entity.User;

/**
 * @author LCB
 * @date 2022/6/29 14:09
 */
public interface UsersService {
    /**
     * 用户登录
     * @param uname
     * @param upwd
     * @return
     */
    public User login(String uname, String upwd)throws RuntimeException;
}
