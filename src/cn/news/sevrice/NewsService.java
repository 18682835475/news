package cn.news.sevrice;

import cn.news.entity.News;
import cn.news.entity.Topic;
import cn.news.entity.User;
import cn.news.utils.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/29 15:20
 */
public interface NewsService {
    /**
     * 后台管理页面  admin.jsp
     * @return
     */
    public List<News> getNewsByList() throws  RuntimeException;

    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws RuntimeException
     */
    public Page<News> getNewsByPage(long pageNumber, long pageSize) throws  RuntimeException;

    /**
     * 查询某主题下最新的前几条新闻
     * @param ntid
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public List<News> getNewsByNtidTop(long ntid,long pageSize)throws SQLException;

    /**
     * 分页查询某主题下的新闻
     * @param ntid
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public Page<News> getNewsByNtidPage(long ntid,long pageNumber,long pageSize)throws SQLException;

    /**
     * 新增新闻
     * @param news
     * @return
     * @throws SQLException
     */
    public boolean add(News news) throws SQLException;

    public News getNewsByNid(long nid) throws Exception;
}
