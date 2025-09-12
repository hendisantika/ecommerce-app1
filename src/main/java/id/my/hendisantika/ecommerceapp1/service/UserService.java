package id.my.hendisantika.ecommerceapp1.service;

import id.my.hendisantika.ecommerceapp1.entity.User;
import id.my.hendisantika.ecommerceapp1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User addUser(User user) {
        try {
            return this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // handle unique constraint violation, e.g., by throwing a custom exception
            throw new RuntimeException("Add user error");
        }
    }

    public User checkLogin(String username, String password) {
        Optional<User> userOpt = this.userRepository.findByUsernameAndPassword(username, password);
        return userOpt.orElse(new User());
    }

    public boolean checkUserExists(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public User getUserByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.orElse(null);
    }
}
