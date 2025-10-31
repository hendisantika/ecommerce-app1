package id.my.hendisantika.ecommerceapp1;

import id.my.hendisantika.ecommerceapp1.config.AbstractTestcontainersTest;
import id.my.hendisantika.ecommerceapp1.controller.AdminController;
import id.my.hendisantika.ecommerceapp1.service.CategoryService;
import id.my.hendisantika.ecommerceapp1.service.ProductService;
import id.my.hendisantika.ecommerceapp1.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdminIntegrationTest extends AbstractTestcontainersTest {

    @Autowired
    private AdminController adminController;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Test
    public void testAdminControllerLoaded() {
        assertNotNull(adminController, "AdminController should be loaded");
        System.out.println("✅ AdminController loaded successfully");
    }

    @Test
    public void testRequiredServicesLoaded() {
        assertNotNull(categoryService, "CategoryService should be loaded");
        assertNotNull(productService, "ProductService should be loaded");
        assertNotNull(userService, "UserService should be loaded");

        System.out.println("✅ All required services loaded successfully");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminCategoriesEndpoint() {
        // This test verifies the controller method works when authenticated
        var result = adminController.getCategory();

        assertNotNull(result, "Categories view should not be null");
        assertEquals("categories", result.getViewName(), "Should return categories view");

        System.out.println("✅ Admin categories endpoint works with authentication");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminProductsEndpoint() {
        // This test verifies the products endpoint works when authenticated
        var result = adminController.getProduct();

        assertNotNull(result, "Products view should not be null");
        assertEquals("products", result.getViewName(), "Should return products view");

        System.out.println("✅ Admin products endpoint works with authentication");
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAdminCustomersEndpoint() {
        // This test verifies the customers endpoint works when authenticated
        var result = adminController.getCustomerDetail();

        assertNotNull(result, "Customers view should not be null");
        assertEquals("displayCustomers", result.getViewName(), "Should return displayCustomers view");

        System.out.println("✅ Admin customers endpoint works with authentication");
    }
}