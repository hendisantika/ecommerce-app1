package id.my.hendisantika.ecommerceapp1.repository;

import id.my.hendisantika.ecommerceapp1.entity.CartProduct;
import id.my.hendisantika.ecommerceapp1.entity.CartProductId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app1
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 13/09/25
 * Time: 05.33
 * To change this template use File | Settings | File Templates.
 */
public interface CartProductRepository extends JpaRepository<CartProduct, CartProductId> {
}
