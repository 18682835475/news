package cn.news.dao;

import cn.news.entity.Comments;
import cn.news.entity.News;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/28 9:05
 */
public interface CommentsDao {
    /**
     * 添加comments评论
     * @param comments
     * @return
     */
    public int addComments(Comments comments);

    /**
     * 通过id删除评论
     * @param cid
     * @return
     */
    public long deleteComments(long cid);

    /**
     * 查询所有评论
     * @return
     */
    public List<Comments> getAll();

    /**
     * 获得评论总数
     * @return
     * @throws SQLException
     */
    public long getCommentsCount() throws SQLException;

    /**
     * 查询某条新闻的评论总数
     * @param cnid
     * @return
     */
    public long getCommentByCnidCount(long cnid);

    /**
     * 查询某新闻下最新的前几条评论
     * @param cnid
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public List<Comments> getCommentsByCnidTop(long cnid, long pageSize)throws SQLException;

    /**
     * 分页查询评论
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws SQLException
     */
    public List<Comments> getCommentsByLimit(long pageNumber,long pageSize) throws SQLException;
}
