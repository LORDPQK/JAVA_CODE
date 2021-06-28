package DAO.impl;

import DAO.PersonDao;
import Person.advanced.impl.PersonRowMapper;
import entity.Person;
import utils.DaoUtils;
import utils.DateUtils;
import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonDaoimpl implements PersonDao {

    private DaoUtils<Person> daoUtils = new DaoUtils();

    @Override
    public int insert(Person person) {

        String sql ="insert into person(name,age,bornDate,email,address)values(?,?,?,?,?);";
        Object[] args ={person.getName(),person.getAge(),DateUtils.utilToSql(person.getBornDate()),person.getEamil(),person.getAddress()};
        return daoUtils.commonsUpdate(sql,args);

    }

    @Override
    public int update(Person person) {

        String sql ="update person set name=?,age=?,bornDate=?,email=?,address=?where id=?";
        Object[] args ={person.getName(),person.getAge(),DateUtils.utilToSql(person.getBornDate()),person.getEamil(),person.getAddress(),person.getId()};
        return daoUtils.commonsUpdate(sql,args);
    }

    @Override
    public int delete(int id) {
        String sql="delete form person where id=?";
        return daoUtils.commonsUpdate(sql,id);
    }

    @Override
    public Person select(int id) {
        String sql ="select * from person where id=?";
        List<Person> list = daoUtils.commonsSelect(sql,new PersonRowMapper(),id);
        if(!list.isEmpty()){
          return  list.get(0);
        }
        return null;
    }

    @Override
    public List<Person> selectAll() {
        String sql = "select * from person";
        List<Person> list = daoUtils.commonsSelect(sql,new PersonRowMapper(),null);
        return list;
    }
    /**
     * 为了通用型考虑使用泛型，daoUtils中的commonsSelect与其定义为泛型，RowMapper作为泛型接口，关键就是RowMapper
     * 的实现类，PersonRowMapper的方法getRow的返回值是Person类型，这时在Daoutils中commonsSelect的泛型就由于
     * 接收了getRow的对象变为Person类型，最终返回一个Person类型的list。
     *
     * Person在用commonsSelect,则返回list集合存的都是Person对象
     * ACC在用commonsSelect,则返回list集合存的都是ACC对象
     */
}
