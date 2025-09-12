package id.my.hendisantika.ecommerceapp1.repository;

import id.my.hendisantika.ecommerceapp1.entity.Cart;
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
 * Time: 05.35
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CartDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public Cart addCart(Cart cart) {
        this.sessionFactory.getCurrentSession().save(cart);
        return cart;
    }

    @Transactional
    public List<Cart> getCarts() {
        return this.sessionFactory.getCurrentSession().createQuery("from CART").list();
    }

//    @Transactional
//    public List<Cart> getCartsByCustomerID(Integer customer_id) {
//        String hql = "from CART where CART.customer_id = :customer_id";
//        return this.sessionFactory.getCurrentSession()
//                .createQuery(hql, Cart.class)
//                .setParameter("customer_id", customer_id)
//                .list();
//    }

    @Transactional
    public void updateCart(Cart cart) {
        this.sessionFactory.getCurrentSession().update(cart);
    }

    @Transactional
    public void deleteCart(Cart cart) {
        this.sessionFactory.getCurrentSession().delete(cart);
    }
}
