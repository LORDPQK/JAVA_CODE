import ocm.pqn.comtroller.dao.UserDAO;
import ocm.pqn.comtroller.entity.User;
import ocm.pqn.comtroller.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSSM {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Test
    public void test1(){
        List<User> users = userDAO.queryUsers();
        for(User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void test2(){
        List<User> users = userService.queryUsers();
        for(User user:users){
            System.out.println(user);
        }
    }
}
