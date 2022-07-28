package cn.news.sevrice.lmpl;

import cn.news.dao.TopicDao;
import cn.news.dao.lmpl.TopicDaoImpl;
import cn.news.entity.Topic;
import cn.news.sevrice.TopicService;
import cn.news.utils.DataBaseUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LCB
 * @date 2022/6/29 15:17
 */
public class TopicServicelmpl implements TopicService {
    /**
     * 新增主题
     *
     * @param topic
     * @return
     * @throws SQLException
     */
    @Override
    public boolean add(Topic topic) throws SQLException {
        Connection connection = null;
        try{
            connection = DataBaseUtils.getConnection();
            TopicDao topicDao = new TopicDaoImpl(connection);
            if(topicDao.add(topic) == 1){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DataBaseUtils.closeAll(connection,null,null);
        }
        return false;
    }

    /**
     * 修改主题
     *
     * @param topic
     * @return
     * @throws SQLException
     */
    @Override
    public boolean update(Topic topic) throws SQLException {
        Connection connection = null;
        try{
            connection = DataBaseUtils.getConnection();
            TopicDao topicDao = new TopicDaoImpl(connection);
            if(topicDao.update(topic) == 1){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DataBaseUtils.closeAll(connection,null,null);
        }
        return false;
    }

    /**
     * 删除主题(物理删除) 事务控制
     *
     * @param tid
     * @return
     * @throws SQLException
     */
    @Override
    public boolean delete(Integer tid) throws SQLException {
        Connection connection = null;
        try{
            connection = DataBaseUtils.getConnection();
            connection.setAutoCommit(false);// 取消自动提交  事务控制
            TopicDao topicDao = new TopicDaoImpl(connection);
            if(topicDao.delete(tid) == 1){
                connection.commit();
                return true;
            }
            connection.rollback();
        }catch (Exception e){
            if(connection != null){connection.rollback();}
            e.printStackTrace();
        }finally {
            DataBaseUtils.closeAll(connection,null,null);
        }
        return false;
    }

    /**
     * 查询所有主题
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Topic> getAll() throws SQLException {
        Connection connection = null;
        List<Topic> topicList = null;
        try{
            connection = DataBaseUtils.getConnection();
            TopicDao topicDao = new TopicDaoImpl(connection);
            topicList = topicDao.getAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DataBaseUtils.closeAll(connection,null,null);
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
        Connection connection = null;
        Topic topic = null;
        try{
            connection = DataBaseUtils.getConnection();
            TopicDao topicDao = new TopicDaoImpl(connection);
            topic = topicDao.getTopicByName(tname);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DataBaseUtils.closeAll(connection,null,null);
        }
        return topic;
    }
}
