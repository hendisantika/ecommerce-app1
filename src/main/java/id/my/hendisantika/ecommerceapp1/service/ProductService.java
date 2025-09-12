package id.my.hendisantika.ecommerceapp1.service;

import id.my.hendisantika.ecommerceapp1.entity.Product;
import id.my.hendisantika.ecommerceapp1.repository.ProductDao;
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
 * Time: 05.40
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> getProducts() {
        return this.productDao.getProducts();
    }

    public Product addProduct(Product product) {
        return this.productDao.addProduct(product);
    }

    public Product getProduct(int id) {
        return this.productDao.getProduct(id);
    }

    public Product updateProduct(int id, Product product) {
        product.setId(id);
        return this.productDao.updateProduct(product);
    }

    public boolean deleteProduct(int id) {
        return this.productDao.deletProduct(id);
    }
}
