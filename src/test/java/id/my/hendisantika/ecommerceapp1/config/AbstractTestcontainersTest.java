package id.my.hendisantika.ecommerceapp1.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

/**
 * Abstract base class for integration tests using Testcontainers.
 * Extend this class to automatically set up a MySQL container for your tests.
 */
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractTestcontainersTest {

    protected static final MySQLContainer<?> mysqlContainer;

    static {
        mysqlContainer = TestcontainersConfiguration.getInstance();
    }

    @DynamicPropertySource
    static void configureDatasource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "com.mysql.cj.jdbc.Driver");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
        registry.add("spring.flyway.enabled", () -> "true");
    }
}
