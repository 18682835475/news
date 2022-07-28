package cn.news.dao;

import cn.news.entity.News;
import cn.news.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/28 9:05
 */
public interface NewsDao {
    /**
     * 查询所有新闻
     * @return
     */
    public List<News> getAll() throws SQLException;

    /**
     * 获得新闻总数
     * @return
     * @throws SQLException
     */
    public long getNewsCount() throws SQLException;

    /**
     * 分页查询新闻
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public List<News> getNewsByLimit(long pageNumber,long pageSize) throws SQLException;

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
    public List<News> getNewsByNtidPage(long ntid,long pageNumber,long pageSize) throws SQLException;

    /**
     * 查询某主题的新闻总条数
     * @param ntid
     * @return
     * @throws SQLException
     */
    public long getNewsByNtidCount(long ntid) throws SQLException;

    /**
     * 新增新闻
     * @param news
     * @return
     * @throws SQLException
     */
    public int add(News news) throws SQLException;

    public News getNewsByNid(long nid) throws Exception;
}
