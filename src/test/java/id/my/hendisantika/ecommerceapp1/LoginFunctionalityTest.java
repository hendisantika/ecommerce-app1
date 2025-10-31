package id.my.hendisantika.ecommerceapp1;

import id.my.hendisantika.ecommerceapp1.config.AbstractTestcontainersTest;
import id.my.hendisantika.ecommerceapp1.entity.User;
import id.my.hendisantika.ecommerceapp1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginFunctionalityTest extends AbstractTestcontainersTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAdminLogin() {
        User adminUser = userService.checkLogin("admin", "admin123");

        assertNotNull(adminUser, "Admin user should not be null");
        assertEquals("admin", adminUser.getUsername(), "Username should match");
        assertEquals("ROLE_ADMIN", adminUser.getRole(), "Admin should have ROLE_ADMIN");

        System.out.println("✅ Admin login test passed!");
        System.out.println("   Username: " + adminUser.getUsername());
        System.out.println("   Role: " + adminUser.getRole());
    }

    @Test
    public void testUserLogin() {
        User regularUser = userService.checkLogin("gojo", "admin123");

        assertNotNull(regularUser, "Regular user should not be null");
        assertEquals("gojo", regularUser.getUsername(), "Username should match");
        assertEquals("ROLE_NORMAL", regularUser.getRole(), "User should have ROLE_NORMAL");

        System.out.println("✅ Regular user login test passed!");
        System.out.println("   Username: " + regularUser.getUsername());
        System.out.println("   Role: " + regularUser.getRole());
    }

    @Test
    public void testInvalidLogin() {
        User invalidUser = userService.checkLogin("admin", "wrongpassword");

        // checkLogin returns empty User object for invalid credentials
        assertTrue(invalidUser.getUsername() == null || invalidUser.getUsername().isEmpty(),
                "Invalid login should return empty user");

        System.out.println("✅ Invalid login test passed!");
    }

    @Test
    public void testPasswordHashing() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String plainPassword = "admin123";
        String hashedPassword = "$2a$10$GCDqksLBioN/aG7AOiUa2OGhjdwXeqs4Gs/inxgTJcUSlOkA6w/EK";

        assertTrue(encoder.matches(plainPassword, hashedPassword),
                "Password should match the hash");

        System.out.println("✅ Password hashing test passed!");
    }
}