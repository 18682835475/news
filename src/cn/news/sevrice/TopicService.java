package cn.news.sevrice;

import cn.news.entity.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/29 15:16
 */
public interface TopicService {

    /**
     * 新增主题
     * @param topic
     * @return
     * @throws SQLException
     */
    public boolean add(Topic topic) throws SQLException;

    /**
     * 修改主题
     * @param topic
     * @return
     * @throws SQLException
     */
    public boolean update(Topic topic) throws SQLException;

    /**
     * 删除主题(物理删除)
     * @param tid
     * @return
     * @throws SQLException
     */
    public boolean delete(Integer tid) throws SQLException;

    /**
     * 查询所有主题
     * @return
     * @throws SQLException
     */
    public List<Topic> getAll() throws SQLException;

    /**
     * 通过tname查询主题
     * @return
     * @throws SQLException
     */
    public Topic getTopicByName(String tname) throws SQLException;

}
