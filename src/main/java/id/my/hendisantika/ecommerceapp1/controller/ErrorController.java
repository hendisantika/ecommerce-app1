package id.my.hendisantika.ecommerceapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app1
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 13/09/25
 * Time: 05.44
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ErrorController {

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
