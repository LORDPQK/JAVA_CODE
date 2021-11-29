package ocm.pqn.comtroller.service.impl;


import ocm.pqn.comtroller.dao.UserDAO;
import ocm.pqn.comtroller.entity.User;
import ocm.pqn.comtroller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //userServiceImpl
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> queryUsers() {
        return userDAO.queryUsers();
    }
}
