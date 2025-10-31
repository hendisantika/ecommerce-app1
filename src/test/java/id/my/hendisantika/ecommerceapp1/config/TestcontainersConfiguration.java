package id.my.hendisantika.ecommerceapp1.config;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public class TestcontainersConfiguration {

    private static MySQLContainer<?> mysqlContainer;

    public static MySQLContainer<?> getInstance() {
        if (mysqlContainer == null) {
            mysqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
                    .withDatabaseName("ecommerce_test")
                    .withUsername("test")
                    .withPassword("test")
                    .withCommand("--character-set-server=utf8mb4",
                            "--collation-server=utf8mb4_unicode_ci",
                            "--max_connections=1000");
            mysqlContainer.start();
        }
        return mysqlContainer;
    }
}
