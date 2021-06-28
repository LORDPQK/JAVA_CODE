package Person.advanced.impl;

import Person.advanced.RowMapper;
import entity.Person;
import utils.DaoUtils;
import utils.DateUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper <Person>{

    //getRow方法对对象进行封装，并以Object类型返回
    @Override
    public Person getRow(ResultSet resultSet) {
        Person person =null;
        try {
            int pid =resultSet.getInt("id");
            String name =resultSet.getString("name");
            int age = resultSet.getInt("age");
            Date borndate = resultSet.getDate("bornDate");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            person = new Person(pid,name,age,borndate,email,address);
            return person;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
