package cn.news.dao.lmpl;

import cn.news.dao.TopicDao;
import cn.news.entity.BaseDao;
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
 * @date 2022/6/27 19:22
 */
public class TopicDaoImpl extends BaseDao implements TopicDao {

    public TopicDaoImpl(Connection connection) {
        super(connection);
    }
    /**
     * 新增主题
     *
     * @param topic
     * @return
     * @throws SQLException
     */
    @Override
    public int add(Topic topic) throws SQLException {
        return  this.executeUpdate("insert into topic(tname) value(?);", new Object[]{topic.getTname()});
    }

    /**
     * 修改主题
     *
     * @param topic
     * @return
     * @throws SQLException
     */
    @Override
    public int update(Topic topic) throws SQLException {
        return  this.executeUpdate("update topic set tname = ? where tid = ?;", new Object[]{topic.getTname(),topic.getTid()});
    }

    /**
     * 删除主题(物理删除)
     *
     * @param tid
     * @return
     * @throws SQLException
     */
    @Override
    public int delete(Integer tid) throws SQLException {
        return  this.executeUpdate("delete from topic where tid = ?;", new Object[]{tid});
    }

    /**
     * 查询所有主题
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Topic> getAll() throws SQLException {
        List<Topic> topicList = new ArrayList<Topic>();
        Topic topic = null;
        ResultSet resultSet = null;
        try{
            String sql = "select tid,tname from topic;";
            resultSet = this.executeQuery(sql,null);
            while(resultSet.next()){
                topic = new Topic();
                topic.setTid(resultSet.getInt("tid"));
                topic.setTname(resultSet.getString("tname"));
                topicList.add(topic);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return topicList;
    }

    /**
     * 通过tname查询主题
     *
     * @param tname
     * @return
     * @throws SQLException
     */
    @Override
    public Topic getTopicByName(String tname) throws SQLException {
        Topic topic = null;
        ResultSet resultSet = null;
        try{
            String sql = "select tid,tname from topic where tname = ?;";
            Object[] params = {tname};
            resultSet = this.executeQuery(sql,params);
            while(resultSet.next()){
                topic = new Topic();
                topic.setTid(resultSet.getInt("tid"));
                topic.setTname(resultSet.getString("tname"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return topic;
    }

}
