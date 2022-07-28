package cn.news.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author LCB
 * @date 2022/6/30 15:10
 */
public class DataBaseUtils {
    public static String DRIVER_NAME;
    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;

    static{
        try {
            // 加载属性集文件
            InputStream inputStream = DataBaseUtils.class.getClassLoader()
                    .getResourceAsStream("database.properties");
            Properties properties = new Properties();
            properties.load(inputStream);// 读取文件内容
            // 读取属性值
            DRIVER_NAME = properties.getProperty("driver");
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭连接
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {resultSet.close();}
            if (statement != null) {statement.close();}
            if (connection != null) {connection.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
