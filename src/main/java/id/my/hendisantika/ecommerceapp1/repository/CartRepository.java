package id.my.hendisantika.ecommerceapp1.repository;

import id.my.hendisantika.ecommerceapp1.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app1
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 13/09/25
 * Time: 06.15
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM CART c WHERE c.customer.id = :customerId")
    List<Cart> findByCustomerId(@Param("customerId") Integer customerId);
}