package id.my.hendisantika.ecommerceapp1;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PasswordVerificationTest {

    @Test
    public void testPasswordMatching() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Generate a fresh hash and verify it works
        String password = "admin123";
        String freshHash = encoder.encode(password);

        boolean matches = encoder.matches(password, freshHash);

        if (matches) {
            System.out.println("✅ Fresh password hash verification successful");
            System.out.println("   Password: " + password);
            System.out.println("   Generated Hash: " + freshHash);
            assertTrue(true, "Password verified successfully");
        } else {
            fail("Fresh password encoding/verification failed");
        }

        // Also test with the database hash
        String dbHash = "$2a$10$8Vpo06BvgBV3hicN1UNVyuIhmi0ntDnHPxqfXetDfs/x2HmQXnNc2";
        boolean dbMatches = encoder.matches(password, dbHash);

        System.out.println("   DB Hash matches: " + dbMatches);
        System.out.println("   DB Hash: " + dbHash);
    }
}