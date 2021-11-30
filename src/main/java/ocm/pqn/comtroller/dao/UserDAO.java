package ocm.pqn.comtroller.dao;

import ocm.pqn.comtroller.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> queryUsers();
}
