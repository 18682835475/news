package cn.news.servlet;

import cn.news.utils.DataBaseUtils;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * jdbc 批量操作（
 *         批量新增 excel文件导入
 *         批量修改 邮箱已读
 *         批量删除 消息删除
 * 步骤：
 *  一： 循环  delete  独立链接-事务独立
 *  二： 循环  delete 一条in语句  数据量要求少
 *  三： 循环  batchExecuteUpdate 一个链接，独立sql
 *         1.预编译SQL connection.prepareStatement(sql);
 *         2.循环赋值
 *              preparedStatement.setObject(1,id);
 *              preparedStatement.addBatch();
 *         3.批量执行
 *              int[] nums = preparedStatement.executeBatch();
 */
public class BatchUpdate {
    private String name = "张三";
    /**
     * 单元测试方法
     *      1.无参
     *      2.无返回值
     */
    @Test
    public void add() throws Exception {
        // 获取连接 jdbc:mysql://localhost:3306/newsmanagersystem?rewriteBatchedStatements=true
        Connection connection = DataBaseUtils.getConnection();
        // 关闭自动提交
        connection.setAutoCommit(false);
        // 预编译sql
        String sql = "insert into comments(cnid,ccontent,cdate,cip,cauthor)value(?,?,?,?,?);";
        // 创建预处理对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        // 批量添加参数
        int num = 1000;// 数量
        for(int i= 1;i<=num;i++){
            pstmt.setInt(1,108);//新闻id
            pstmt.setString(2,"评论内容！！！"+i);
            pstmt.setString(3,"2022-01-01 12:11:14");
            pstmt.setString(4,"localhost");
            pstmt.setString(5,"user1");
            pstmt.addBatch();// 批量预处理sql和参数
        }
        // 批量处理
        int[] nums = pstmt.executeBatch();
        System.out.println(Arrays.toString(nums));
        // 提交
        connection.commit();
    }

    @Test
    public void del() throws SQLException {
        System.out.println("del");
        int[] cids = {1000,1001,1002,1003,1004,1005,1006,1007,1008,1009};
        // 获取连接 jdbc:mysql://localhost:3306/newsmanagersystem?rewriteBatchedStatements=true
        Connection connection = DataBaseUtils.getConnection();
        // 关闭自动提交
        connection.setAutoCommit(false);
        // 预编译sql
        String sql = "delete from comments where cid = ? ";
        // 创建预处理对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        // 批量添加参数
        for(int i= 0;i<cids.length;i++){
            pstmt.setInt(1,cids[i]);//新闻id
            pstmt.addBatch();// 批量预处理sql和参数
        }
        // 批量处理
        int[] nums = pstmt.executeBatch();
        System.out.println(Arrays.toString(nums));
        // 提交
        connection.commit();
    }
}