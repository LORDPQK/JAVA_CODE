package utils;

import Person.advanced.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class DaoUtils<T> {


    /**
     * 公共处理增删改的方法
     * @param sql 执行的sql语句
     * @param args 参数列表
     * @return 受影响函数
     */
    //传入可变长参数列表 Object... args
    public int commonsUpdate(String sql,Object... args){
        Connection connection =null;
        PreparedStatement preparedStatement =null;

        connection =DbUtils.getConnection();

        try {
            preparedStatement = connection.prepareStatement(sql);

            for (int i= 0;i<args.length;i++){
                preparedStatement.setObject(i+1,args[i]);
            }

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            DbUtils.CloseAll(null,preparedStatement,null);
        }
        return 0;
    }

    /**
     *公共查询方法
     * @param sql 执行的sql语句
     * @param args 参数列表
     * @return 集合
     */
    public List<T> commonsSelect(String sql, RowMapper<T> rowMapper, Object... args){
        Connection connection =null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        List<T> list =new ArrayList<>();

        connection=DbUtils.getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);

            if(args!=null){
                for(int i =0; i<args.length;i++){
                    //填充sql语句中占位符
                    preparedStatement.setObject(i+1,args[i]);
                }
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                //如何根据查询结果完成ORM，如何进行对象的创建及赋值
                T t = rowMapper.getRow(resultSet);//从resultSet拿到一行数据，给了rowMapper,返回一个object类型对象
                //回调-》》调用者提供的一个封装方法OEM
                list.add(t);//让调用者进行对象的封装,该方法负责将对象放到集合中。
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }



}
