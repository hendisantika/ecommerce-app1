package id.my.hendisantika.ecommerceapp1.service;

import id.my.hendisantika.ecommerceapp1.entity.Cart;
import id.my.hendisantika.ecommerceapp1.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app1
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 13/09/25
 * Time: 05.38
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CartService {
    @Autowired
    private CartDao cartDao;

    public Cart addCart(Cart cart) {
        return cartDao.addCart(cart);
    }

    //    public Cart getCart(int id)
//    {
//        return cartDao.getCart(id);
//    }
    public List<Cart> getCarts() {
        return this.cartDao.getCarts();
    }

    public void updateCart(Cart cart) {
        cartDao.updateCart(cart);
    }

    public void deleteCart(Cart cart) {
        cartDao.deleteCart(cart);
    }

//    pubiic List<Cart> getCartByUserId(int customer_id){
//        return cartDao.getCartsByCustomerID(customer_id);
//    }
}
