package view;

import DAO.PersonDao;
import DAO.impl.PersonDaoimpl;
import entity.Person;
import utils.DaoUtils;
import utils.DateUtils;

public class TestPerson {
    public static void main(String[] args) {
        PersonDao personDao = new PersonDaoimpl();

        //插入test
      //  Person person =new Person("jack",19, DateUtils.strToUtil("2000-10-30"),"jack@163.com","SHANGHAI");
      //  int result= personDao.insert(person);
      //  System.out.println(result);

        //查询test
     Person person  = personDao.select(2);
     System.out.println(person);
    }
}
