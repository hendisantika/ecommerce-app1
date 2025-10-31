package id.my.hendisantika.ecommerceapp1;

import id.my.hendisantika.ecommerceapp1.config.AbstractTestcontainersTest;
import id.my.hendisantika.ecommerceapp1.entity.User;
import id.my.hendisantika.ecommerceapp1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KibaUserTest extends AbstractTestcontainersTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testKibaUserExists() {
        User kibaUser = userService.getUserByUsername("kiba");
        assertNotNull(kibaUser, "Kiba user should exist in database");
        assertEquals("ROLE_ADMIN", kibaUser.getRole(), "Kiba should have admin role");

        System.out.println("✅ Kiba user found in database");
        System.out.println("   Username: " + kibaUser.getUsername());
        System.out.println("   Role: " + kibaUser.getRole());
        System.out.println("   Email: " + kibaUser.getEmail());
    }

    @Test
    public void testKibaPasswordVerification() {
        User kibaUser = userService.getUserByUsername("kiba");
        assertNotNull(kibaUser, "Kiba user should exist");

        // Test password verification
        boolean passwordMatches = passwordEncoder.matches("12345", kibaUser.getPassword());
        assertTrue(passwordMatches, "Password '12345' should match kiba's hashed password");

        System.out.println("✅ Kiba password verification successful");
        System.out.println("   Password: 12345");
        System.out.println("   Hash: " + kibaUser.getPassword());
    }
}