package servletProject.utils;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtils {
    private static DruidDataSource ds;
    private static final ThreadLocal<Connection> THREAD_LOCAL =new ThreadLocal<>();

    static {
        Properties properties =new Properties();
        InputStream inputStream=DbUtils.class.getResourceAsStream("/database.properties");

        try {
            properties.load(inputStream);
            ds =(DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConncetion(){
        Connection connection =THREAD_LOCAL.get();
        try {
            if(connection ==null){
                connection =ds.getConnection();
                THREAD_LOCAL.set(connection);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void begin(){
        Connection conncetion =null;
        try {
            conncetion = getConncetion();
            conncetion.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void commit(){
        Connection connection =null;
        try {
            connection = getConncetion();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            closeAll(connection,null,null);
        }
    }

    public static void rollback(){
        Connection connection =null;
        try {
            connection =getConncetion();
            connection.rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            closeAll(connection,null,null);
        }

    }

    public static void closeAll(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(statement !=null){
                statement.close();
            }
            if(resultSet !=null){
                resultSet.close();
            }
            if(connection!=null){
                connection.close();
                THREAD_LOCAL.remove();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
