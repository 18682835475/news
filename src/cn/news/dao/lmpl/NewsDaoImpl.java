package cn.news.dao.lmpl;

import cn.news.dao.NewsDao;
import cn.news.entity.BaseDao;
import cn.news.entity.News;
import cn.news.entity.Topic;
import cn.news.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/29 11:23
 */
public class NewsDaoImpl extends BaseDao implements NewsDao {
    public NewsDaoImpl(Connection connection) {
        super(connection);
    }

    /**
     * 查询所有新闻
     *
     * @return
     */
    @Override
    public List<News> getAll() throws SQLException {
        List<News> newsList = new ArrayList<News>();
        ResultSet resultSet = null;
        try{
            String sql = "select nid,ntitle,nauthor from news;";
            resultSet = this.executeQuery(sql,null);
            while(resultSet.next()){
                News news = new News();
                news.setNid(resultSet.getInt("nid"));
                news.setNtitle(resultSet.getString("ntitle"));
                news.setNauthor(resultSet.getString("nauthor"));
                newsList.add(news);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return newsList;
    }

    /**
     * 获得新闻总数
     *
     * @return
     * @throws SQLException
     */
    @Override
    public long getNewsCount() throws SQLException {
        long count = 0;
        ResultSet resultSet = null;
        try{
            String sql = "select count(nid) as count from news;";
            resultSet = this.executeQuery(sql,null);
            while(resultSet.next()){
                count = resultSet.getLong("count");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 分页查询新闻
     *
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Override
    public List<News> getNewsByLimit(long pageNumber, long pageSize) throws SQLException {
        List<News> newsList = new ArrayList<News>();
        ResultSet resultSet = null;
        try{
            String sql = "select nid,ntitle,nauthor from news order by ncreateDate desc limit ?,?;";
            resultSet = this.executeQuery(sql,new Object[]{(pageNumber-1)*pageSize,pageSize});
            while(resultSet.next()){
                News news = new News();
                news.setNid(resultSet.getInt("nid"));
                news.setNtitle(resultSet.getString("ntitle"));
                news.setNauthor(resultSet.getString("nauthor"));
                newsList.add(news);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return newsList;
    }

    /**
     * 查询某主题下最新的前几条新闻
     *
     * @param ntid
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Override
    public List<News> getNewsByNtidTop(long ntid, long pageSize) throws SQLException {
        List<News> newsList = new ArrayList<News>();
        ResultSet resultSet = null;
        try{
            String sql = "select nid,ntitle from news where ntid = ? order by ncreateDate desc limit ?;";
            resultSet = this.executeQuery(sql,new Object[]{ntid,pageSize});
            while(resultSet.next()){
                News news = new News();
                news.setNid(resultSet.getInt("nid"));
                news.setNtitle(resultSet.getString("ntitle"));
                newsList.add(news);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return newsList;
    }

    /**
     * 分页查询某主题下的新闻
     *
     * @param ntid
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Override
    public List<News> getNewsByNtidPage(long ntid, long pageNumber, long pageSize) throws SQLException {
        List<News> newsList = new ArrayList<News>();
        ResultSet resultSet = null;
        try{
            String sql = "select nid,ntitle,ncreateDate from news where ntid = ? order by ncreateDate desc limit ?,?;";
            resultSet = this.executeQuery(sql,new Object[]{ntid,(pageNumber-1)*pageSize,pageSize});
            while(resultSet.next()){
                News news = new News();
                news.setNid(resultSet.getInt("nid"));
                news.setNtitle(resultSet.getString("ntitle"));
                news.setNcreateDate(resultSet.getTimestamp("ncreateDate"));
                newsList.add(news);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return newsList;
    }

    /**
     * 查询某主题的新闻总条数
     *
     * @param ntid
     * @return
     * @throws SQLException
     */
    @Override
    public long getNewsByNtidCount(long ntid) throws SQLException {
        long count = 0;
        ResultSet resultSet = null;
        try{
            String sql = "select count(nid) as count from news where ntid=?;";
            resultSet = this.executeQuery(sql,new Object[]{ntid});
            while(resultSet.next()){
                count = resultSet.getLong("count");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 新增新闻
     *
     * @param news
     * @return
     * @throws SQLException
     */
    @Override
    public int add(News news) throws SQLException {
        String sql = "INSERT INTO `NEWS`(`ntid`, `ntitle`, `nauthor`, `ncreateDate`," +
                " `npicPath`, `ncontent`, `nmodifyDate`, `nsummary`) " +
                "values(?,?,?,?,?,?,?,?);";
        Object[] params = {
                news.getNtid(),news.getNtitle(),news.getNauthor(),news.getNcreateDate(),
                news.getNpicPath(), news.getNcontent(),news.getNmodifyDate(),news.getNsummary()
        };
        return  this.executeUpdate(sql, params);
    }

    @Override
    public News getNewsByNid(long nid) throws Exception {
        return null;
    }
}
