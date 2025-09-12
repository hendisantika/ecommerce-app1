package id.my.hendisantika.ecommerceapp1.service;

import id.my.hendisantika.ecommerceapp1.entity.User;
import id.my.hendisantika.ecommerceapp1.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app1
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 13/09/25
 * Time: 05.41
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getUsers() {
        return this.userDao.getAllUser();
    }

    public User addUser(User user) {
        try {
            return this.userDao.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            // handle unique constraint violation, e.g., by throwing a custom exception
            throw new RuntimeException("Add user error");
        }
    }

    public User checkLogin(String username, String password) {
        return this.userDao.getUser(username, password);
    }

    public boolean checkUserExists(String username) {
        return this.userDao.userExists(username);
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
