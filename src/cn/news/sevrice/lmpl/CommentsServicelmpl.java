package cn.news.sevrice.lmpl;

import cn.news.dao.CommentsDao;
import cn.news.dao.NewsDao;
import cn.news.dao.lmpl.CommentsDaoImpl;
import cn.news.dao.lmpl.NewsDaoImpl;
import cn.news.entity.Comments;
import cn.news.entity.News;
import cn.news.sevrice.CommentsService;
import cn.news.utils.DataBaseUtils;
import cn.news.utils.Page;
import com.alibaba.fastjson.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/29 15:22
 */
public class CommentsServicelmpl implements CommentsService {

    @Override
    public List<Comments> getCommentsByCnidTop(long cnid, long pageSize) throws SQLException {
        Connection connection = null;
        List<Comments> newsList = null;
        try {
            connection = DataBaseUtils.getConnection();
            CommentsDao commentsDao = new CommentsDaoImpl(connection);
            newsList = commentsDao.getCommentsByCnidTop(cnid,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataBaseUtils.closeAll(connection, null, null);
        }
        return newsList;
    }

    @Override
    public boolean addComments(Comments comments) throws SQLException {
        Connection connection = null;
        try {
            connection = DataBaseUtils.getConnection();
            connection.setAutoCommit(false);
            Date date = new Date();
            comments.setCdate(date);
            CommentsDao commentsDao = new CommentsDaoImpl(connection);
            if (commentsDao.addComments(comments) == 1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
            }
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
        } finally {
            DataBaseUtils.closeAll(connection, null, null);
        }
        return false;
    }

    @Override
    public Page<Comments> getCommentsByPage(long pageNumber, long pageSize) throws SQLException {
        Connection connection = null;
        Page<Comments> page = new Page<Comments>();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        try{
            connection = DataBaseUtils.getConnection();
            CommentsDao commentsDao = new CommentsDaoImpl(connection);
            page.setCount(commentsDao.getCommentsCount());
            page.setData(commentsDao.getCommentsByLimit(pageNumber,pageSize));
            System.out.println(JSONObject.toJSONString(page));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DataBaseUtils.closeAll(connection,null,null);
        }
        return page;
    }
}
