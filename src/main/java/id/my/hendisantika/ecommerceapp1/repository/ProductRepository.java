package id.my.hendisantika.ecommerceapp1.repository;

import id.my.hendisantika.ecommerceapp1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
public interface ProductRepository extends JpaRepository<Product, Integer> {
}