package id.my.hendisantika.ecommerceapp1.controller;

import id.my.hendisantika.ecommerceapp1.entity.Category;
import id.my.hendisantika.ecommerceapp1.entity.Product;
import id.my.hendisantika.ecommerceapp1.entity.User;
import id.my.hendisantika.ecommerceapp1.service.CategoryService;
import id.my.hendisantika.ecommerceapp1.service.ProductService;
import id.my.hendisantika.ecommerceapp1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app1
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 13/09/25
 * Time: 05.43
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;


    @GetMapping("/index")
    public String index(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("login")
    public ModelAndView adminlogin(@RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView("adminlogin");
        if ("true".equals(error)) {
            mv.addObject("msg", "Invalid username or password. Please try again.");
        }
        return mv;
    }

    @GetMapping(value = {"/", "dashboard"})
    public ModelAndView adminHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView mv = new ModelAndView("adminHome");
        mv.addObject("admin", authentication.getName());
        return mv;
    }

    @GetMapping("categories")
    public ModelAndView getCategory() {
        ModelAndView mView = new ModelAndView("categories");
        List<Category> categories = this.categoryService.getCategories();
        mView.addObject("categories", categories);
        return mView;
    }

    @PostMapping("/categories")
    public String addCategory(@RequestParam("categoryname") String category_name) {
        System.out.println(category_name);

        Category category = this.categoryService.addCategory(category_name);
        if (category.getName().equals(category_name)) {
            return "redirect:categories";
        } else {
            return "redirect:categories";
        }
    }

    @GetMapping("categories/delete")
    public String removeCategoryDb(@RequestParam("id") int id) {
        this.categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("categories/update")
    public String updateCategory(@RequestParam("categoryid") int id, @RequestParam("categoryname") String categoryname) {
        Category category = this.categoryService.updateCategory(id, categoryname);
        return "redirect:/admin/categories";
    }


    //	 --------------------------Remaining --------------------
    @GetMapping("products")
    public ModelAndView getProduct() {
        ModelAndView mView = new ModelAndView("products");

        List<Product> products = this.productService.getProducts();

        if (products.isEmpty()) {
            mView.addObject("msg", "No products are available");
        } else {
            mView.addObject("products", products);
        }
        return mView;
    }

    @GetMapping("products/add")
    public ModelAndView addProduct() {
        ModelAndView mView = new ModelAndView("productsAdd");
        List<Category> categories = this.categoryService.getCategories();
        mView.addObject("categories", categories);
        return mView;
    }

    @RequestMapping(value = "products/add", method = RequestMethod.POST)
    public String addProduct(@RequestParam("name") String name, @RequestParam("categoryid") int categoryId, @RequestParam("price") int price, @RequestParam("weight") int weight, @RequestParam("quantity") int quantity, @RequestParam("description") String description, @RequestParam("productImage") String productImage) {
        System.out.println(categoryId);
        Category category = this.categoryService.getCategory(categoryId);
        Product product = new Product();
        product.setId(categoryId);
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(productImage);
        product.setWeight(weight);
        product.setQuantity(quantity);
        this.productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("products/update/{id}")
    public ModelAndView updateProduct(@PathVariable("id") int id) {

        ModelAndView mView = new ModelAndView("productsUpdate");
        Product product = this.productService.getProduct(id);
        List<Category> categories = this.categoryService.getCategories();

        mView.addObject("categories", categories);
        mView.addObject("product", product);
        return mView;
    }

    @RequestMapping(value = "products/update/{id}", method = RequestMethod.POST)
    public String updateProduct(@PathVariable("id") int id, @RequestParam("name") String name, @RequestParam("categoryid") int categoryId, @RequestParam("price") int price, @RequestParam("weight") int weight, @RequestParam("quantity") int quantity, @RequestParam("description") String description, @RequestParam("productImage") String productImage) {
//		this.productService.updateProduct();
        return "redirect:/admin/products";
    }

    @GetMapping("products/delete")
    public String removeProduct(@RequestParam("id") int id) {
        this.productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @PostMapping("products")
    public String postProduct() {
        return "redirect:/admin/categories";
    }

    @GetMapping("customers")
    public ModelAndView getCustomerDetail() {
        ModelAndView mView = new ModelAndView("displayCustomers");
        List<User> users = this.userService.getUsers();
        mView.addObject("customers", users);
        return mView;
    }


    @GetMapping("profileDisplay")
    public String profileDisplay(Model model) {
        String displayusername, displaypassword, displayemail, displayaddress;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommjava", "root", "");
            PreparedStatement stmt = con.prepareStatement("select * from users where username = ?" + ";");

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            stmt.setString(1, username);

            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                int userid = rst.getInt(1);
                displayusername = rst.getString(2);
                displayemail = rst.getString(3);
                displaypassword = rst.getString(4);
                displayaddress = rst.getString(5);
                model.addAttribute("userid", userid);
                model.addAttribute("username", displayusername);
                model.addAttribute("email", displayemail);
                model.addAttribute("password", displaypassword);
                model.addAttribute("address", displayaddress);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        System.out.println("Hello");
        return "updateProfile";
    }

    @RequestMapping(value = "updateuser", method = RequestMethod.POST)
    public String updateUserProfile(@RequestParam("userid") int userid, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("address") String address) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommjava", "root", "");

            PreparedStatement pst = con.prepareStatement("update users set username= ?,email = ?,password= ?, address= ? where uid = ?;");
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, address);
            pst.setInt(5, userid);
            int i = pst.executeUpdate();

            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return "redirect:index";
    }
}
