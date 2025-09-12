package id.my.hendisantika.ecommerceapp1.service;

import id.my.hendisantika.ecommerceapp1.entity.Cart;
import id.my.hendisantika.ecommerceapp1.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCart(int id) {
        Optional<Cart> cartOpt = cartRepository.findById(id);
        return cartOpt.orElse(null);
    }

    public List<Cart> getCarts() {
        return this.cartRepository.findAll();
    }

    public void updateCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    public List<Cart> getCartByUserId(int customerId) {
        return cartRepository.findByCustomerId(customerId);
    }
}
