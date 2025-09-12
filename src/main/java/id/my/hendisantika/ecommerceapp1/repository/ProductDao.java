package id.my.hendisantika.ecommerceapp1.repository;

import id.my.hendisantika.ecommerceapp1.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : ecommerce-app1
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 13/09/25
 * Time: 05.37
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ProductDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public List<Product> getProducts() {
        return this.sessionFactory.getCurrentSession().createQuery("from PRODUCT").list();
    }

    @Transactional
    public Product addProduct(Product product) {
        this.sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Transactional
    public Product getProduct(int id) {
        return this.sessionFactory.getCurrentSession().get(Product.class, id);
    }

    public Product updateProduct(Product product) {
        this.sessionFactory.getCurrentSession().update(String.valueOf(Product.class), product);
        return product;
    }

    @Transactional
    public Boolean deletProduct(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        Object persistanceInstance = session.load(Product.class, id);

        if (persistanceInstance != null) {
            session.delete(persistanceInstance);
            return true;
        }
        return false;
    }
}
