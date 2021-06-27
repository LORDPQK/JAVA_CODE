package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/*
        链接数据库
        事务的控制
        资源释放
 */

public class DbUtils {
    //储存配置文件的map
    private static final Properties PROPERTIES = new Properties();
    private static final ThreadLocal<Connection> THREAD_LOCAL = new ThreadLocal<>();

    static{
        //通过本类自带流，读取db.properties配置文件
        InputStream is =DbUtils.class.getResourceAsStream("/db.properties");
        try {
            PROPERTIES.load(is);//通过流将配置文件内容加载到properties集合中
            Class.forName(PROPERTIES.getProperty("driver"));//加载驱动
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection =THREAD_LOCAL.get();
        try {
            if(connection==null){
                connection= DriverManager.getConnection(PROPERTIES.getProperty("url"),PROPERTIES.getProperty("username"),PROPERTIES.getProperty("password"));
                THREAD_LOCAL.set(connection);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;

    }

    //开启事务
    public static void begin(){
        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //提交事务
    public static  void commit(){
        Connection connection = null;
        try {
            connection = getConnection();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            CloseAll(connection,null,null);
        }
    }

    //回滚事务
    public static void rollback(){
        Connection connection = null;
        try {
            connection = getConnection();
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            CloseAll(connection,null,null);
        }
    }

    //关闭数据库链接
    public static void CloseAll(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
