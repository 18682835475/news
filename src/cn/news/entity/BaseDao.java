package cn.news.entity;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author LCB
 * @date 2022/6/27 19:28
 */
public class BaseDao {
    private Connection connection;

    public BaseDao(Connection connection){
        this.connection = connection;
    }

    /**
     * 修改方法
     * @param sql
     * @param params
     * @return
     */
    public int executeUpdate(String sql,Object[] params){
        int result = 0;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            if(params != null){
                for (int i=0; i<params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            result = preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询方法
     * @param sql
     * @param params
     * @return
     */
    public ResultSet executeQuery(String sql,Object[] params){
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            if(params != null){
                for (int i=0;i<params.length;i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
