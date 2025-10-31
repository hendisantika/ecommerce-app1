package id.my.hendisantika.ecommerceapp1;

import id.my.hendisantika.ecommerceapp1.config.AbstractTestcontainersTest;
import id.my.hendisantika.ecommerceapp1.entity.User;
import id.my.hendisantika.ecommerceapp1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminAccessTest extends AbstractTestcontainersTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void testAdminUserExists() {
        User adminUser = userService.getUserByUsername("admin");

        assertNotNull(adminUser, "Admin user should exist in database");
        assertEquals("admin", adminUser.getUsername(), "Username should be admin");
        assertEquals("ROLE_ADMIN", adminUser.getRole(), "Admin should have ROLE_ADMIN");

        System.out.println("✅ Admin user exists in database");
        System.out.println("   Username: " + adminUser.getUsername());
        System.out.println("   Role: " + adminUser.getRole());
    }

    @Test
    public void testAdminAuthentication() {
        User adminUser = userService.checkLogin("admin", "admin123");

        assertNotNull(adminUser, "Admin login should succeed");
        assertEquals("admin", adminUser.getUsername(), "Username should match");
        assertEquals("ROLE_ADMIN", adminUser.getRole(), "Role should be ROLE_ADMIN");

        System.out.println("✅ Admin authentication successful");
        System.out.println("   Username: " + adminUser.getUsername());
        System.out.println("   Role: " + adminUser.getRole());
    }

    @Test
    public void testAdminSpringSecurityIntegration() {
        UserDetails adminDetails = userDetailsService.loadUserByUsername("admin");

        assertNotNull(adminDetails, "Admin should load in Spring Security");
        assertEquals("admin", adminDetails.getUsername(), "Username should match");
        assertTrue(adminDetails.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")),
                "Admin should have ROLE_ADMIN authority");

        System.out.println("✅ Spring Security integration working");
        System.out.println("   Username: " + adminDetails.getUsername());
        System.out.println("   Authorities: " + adminDetails.getAuthorities());
    }
}