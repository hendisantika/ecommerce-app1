package id.my.hendisantika.ecommerceapp1.service;

import id.my.hendisantika.ecommerceapp1.entity.Product;
import id.my.hendisantika.ecommerceapp1.repository.ProductRepository;
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
 * Time: 05.40
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Product getProduct(int id) {
        Optional<Product> productOpt = this.productRepository.findById(id);
        return productOpt.orElse(null);
    }

    public Product updateProduct(int id, Product product) {
        product.setId(id);
        return this.productRepository.save(product);
    }

    public boolean deleteProduct(int id) {
        try {
            this.productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
