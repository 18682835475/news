package cn.news.dao.lmpl;

import cn.news.dao.CommentsDao;
import cn.news.dao.NewsDao;
import cn.news.entity.BaseDao;
import cn.news.entity.Comments;
import cn.news.entity.News;
import cn.news.utils.DataBaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/29 11:24
 */
public class CommentsDaoImpl extends BaseDao implements CommentsDao {
    public CommentsDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int addComments(Comments comments) {
        String sql = "INSERT INTO `comments`(`cnid`, `ccontent`, `cip`,`cauthor`) values(?,?,?,?);";
        Object[] params = {
                comments.getCnid(),comments.getCcontent(),comments.getCip(),comments.getCauthor()
        };
        return  this.executeUpdate(sql, params);
    }

    @Override
    public long deleteComments(long cid) {
        return  this.executeUpdate("delete from comments where cid = ?;", new Object[]{cid});
    }

    @Override
    public List<Comments> getAll() {
        List<Comments> newsList = new ArrayList<Comments>();
        ResultSet resultSet = null;
        try{
            String sql = "select cid,ccontent,cauthor from comments;";
            resultSet = this.executeQuery(sql,null);
            while(resultSet.next()){
                Comments comments = new Comments();
                comments.setCid(resultSet.getInt("cid"));
                comments.setCcontent(resultSet.getString("ccontent"));
                comments.setCauthor(resultSet.getString("cauthor"));
                newsList.add(comments);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    public long getCommentsCount() throws SQLException {
        long count = 0;
        ResultSet resultSet = null;
        try{
            String sql = "select count(cid) as count from comments;";
            resultSet = this.executeQuery(sql,null);
            while(resultSet.next()){
                count = resultSet.getLong("count");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public long getCommentByCnidCount(long cnid) {
        long count = 0;
        ResultSet resultSet = null;
        try{
            String sql = "select count(cnid) as count from comments where cnid=?;";
            resultSet = this.executeQuery(sql,new Object[]{cnid});
            while(resultSet.next()){
                count = resultSet.getLong("count");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Comments> getCommentsByCnidTop(long cnid, long pageSize) throws SQLException {
        List<Comments> newsList = new ArrayList<Comments>();
        ResultSet resultSet = null;
        try{
            String sql = "select cid,ccontent from comments where cnid = ? order by cdate desc limit ?;";
            resultSet = this.executeQuery(sql,new Object[]{cnid,pageSize});
            while(resultSet.next()){
                Comments comments = new Comments();
                comments.setCid(resultSet.getInt("cid"));
                comments.setCcontent(resultSet.getString("ccontent"));
                comments.setCdate(resultSet.getTimestamp("cdate"));
                newsList.add(comments);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    public List<Comments> getCommentsByLimit(long pageNumber, long pageSize) throws SQLException {
        List<Comments> newsList = new ArrayList<Comments>();
        ResultSet resultSet = null;
        try{
            String sql = "select cid,ccontent,cauthor from comments order by cdate desc limit ?,?;";
            resultSet = this.executeQuery(sql,new Object[]{(pageNumber-1)*pageSize,pageSize});
            while(resultSet.next()){
                Comments comments = new Comments();
                comments.setCid(resultSet.getInt("cid"));
                comments.setCcontent(resultSet.getString("ccontent"));
                comments.setCauthor(resultSet.getString("cauthor"));
                newsList.add(comments);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return newsList;
    }
}
