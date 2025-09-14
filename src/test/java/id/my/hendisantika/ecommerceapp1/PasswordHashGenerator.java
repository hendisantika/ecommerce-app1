package id.my.hendisantika.ecommerceapp1;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {

    @Test
    public void generatePasswordHashes() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String plainPassword = "admin123";
        String hashedPassword = encoder.encode(plainPassword);

        System.out.println("Plain password: " + plainPassword);
        System.out.println("Hashed password: " + hashedPassword);
        System.out.println("Verification: " + encoder.matches(plainPassword, hashedPassword));
    }
}