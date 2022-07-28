package cn.news.sevrice;

import cn.news.dao.CommentsDao;
import cn.news.dao.lmpl.CommentsDaoImpl;
import cn.news.entity.Comments;
import cn.news.entity.News;
import cn.news.utils.Page;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/29 15:21
 */
public interface CommentsService {

    /**
     * 查询某主题下最新的前几条新闻
     * @param cnid
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public List<Comments> getCommentsByCnidTop(long cnid, long pageSize)throws SQLException;

    /**
     * 添加comments评论
     * @param comments
     * @return
     */
    public boolean addComments(Comments comments) throws SQLException;

    /**
     * 分页查询评论
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public Page<Comments> getCommentsByPage(long pageNumber,long pageSize) throws SQLException;


}
