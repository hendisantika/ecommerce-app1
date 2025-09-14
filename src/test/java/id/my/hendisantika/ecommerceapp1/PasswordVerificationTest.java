package id.my.hendisantika.ecommerceapp1;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PasswordVerificationTest {

    @Test
    public void testPasswordMatching() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = "$2a$08$MzAmZ8dJhup1NqLg.6pZ8uZ/sysgZL1PAIe7v5dg.oNFVxxEFWVju";

        // Test common passwords
        String[] testPasswords = {"admin123", "password", "123456", "admin", "test"};

        for (String testPassword : testPasswords) {
            if (encoder.matches(testPassword, hashedPassword)) {
                System.out.println("✅ Password match found: '" + testPassword + "'");
                assertTrue(true, "Password verified successfully");
                return;
            }
        }

        System.out.println("❌ No password match found for the given hash");
        fail("Could not verify the correct password");
    }
}