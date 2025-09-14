package id.my.hendisantika.ecommerceapp1.service;

import id.my.hendisantika.ecommerceapp1.entity.User;
import id.my.hendisantika.ecommerceapp1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User addUser(User user) {
        try {
            // Hash the password before saving
            if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            return this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // handle unique constraint violation, e.g., by throwing a custom exception
            throw new RuntimeException("Add user error");
        }
    }

    public User checkLogin(String username, String password) {
        Optional<User> userOpt = this.userRepository.findByUsername(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt.get();
        }
        return new User();
    }

    public boolean checkUserExists(String username) {
        return this.userRepository.existsByUsername(username);
    }

    public User getUserByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.orElse(null);
    }
}
