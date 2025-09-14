package id.my.hendisantika.ecommerceapp1.controller;

import id.my.hendisantika.ecommerceapp1.entity.Product;
import id.my.hendisantika.ecommerceapp1.entity.User;
import id.my.hendisantika.ecommerceapp1.service.ProductService;
import id.my.hendisantika.ecommerceapp1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app1
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 13/09/25
 * Time: 05.45
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    @GetMapping("/buy")
    public String buy() {
        return "buy";
    }

    @GetMapping("/login")
    public ModelAndView userLogin(@RequestParam(required = false) String error) {
        ModelAndView mv = new ModelAndView("userLogin");
        if ("true".equals(error)) {
            mv.addObject("msg", "Please enter correct email and password");
        }
        return mv;
    }

    @GetMapping("/")
    public ModelAndView indexPage() {
        ModelAndView mView = new ModelAndView("index");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        mView.addObject("username", username);
        List<Product> products = this.productService.getProducts();

        if (products.isEmpty()) {
            mView.addObject("msg", "No products are available");
        } else {
            mView.addObject("products", products);
        }
        return mView;
    }

    @GetMapping("/user/products")
    public ModelAndView getProduct() {

        ModelAndView mView = new ModelAndView("uproduct");

        List<Product> products = this.productService.getProducts();

        if (products.isEmpty()) {
            mView.addObject("msg", "No products are available");
        } else {
            mView.addObject("products", products);
        }

        return mView;
    }

    @PostMapping(value = "newuserregister")
    public ModelAndView newUserRegister(@ModelAttribute User user) {
        // Check if username already exists in database
        boolean exists = this.userService.checkUserExists(user.getUsername());

        if (!exists) {
            log.info(user.getEmail());
            user.setRole("ROLE_NORMAL");
            this.userService.addUser(user);

            log.info("New user created: {}", user.getUsername());
            ModelAndView mView = new ModelAndView("userLogin");
            return mView;
        } else {
            log.info("New user not created - username taken: {}", user.getUsername());
            ModelAndView mView = new ModelAndView("register");
            mView.addObject("msg", user.getUsername() + " is taken. Please choose a different username.");
            return mView;
        }
    }

    @GetMapping("/profileDisplay")
    public String profileDisplay(Model model, HttpServletRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);

        if (user != null) {
            model.addAttribute("userid", user.getId());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("password", user.getPassword());
            model.addAttribute("address", user.getAddress());
        } else {
            model.addAttribute("msg", "User not found");
        }

        return "updateProfile";
    }


    //for Learning purpose of model
    @GetMapping("/test")
    public String Test(Model model) {
        System.out.println("test page");
        model.addAttribute("author", "jay gajera");
        model.addAttribute("id", 40);

        List<String> friends = new ArrayList<String>();
        model.addAttribute("f", friends);
        friends.add("xyz");
        friends.add("abc");

        return "test";
    }

    // for learning purpose of model and view ( how data is pass to view)

    @GetMapping("/test2")
    public ModelAndView Test2() {
        System.out.println("test page");
        //create modelandview object
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "jay gajera 17");
        mv.addObject("id", 40);
        mv.setViewName("test2");

        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(25);
        mv.addObject("marks", list);
        return mv;


    }


//	@GetMapping("carts")
//	public ModelAndView  getCartDetail()
//	{
//		ModelAndView mv= new ModelAndView();
//		List<Cart>carts = cartService.getCarts();
//	}
}
