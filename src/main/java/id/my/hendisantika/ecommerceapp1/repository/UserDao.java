package id.my.hendisantika.ecommerceapp1.repository;

import id.my.hendisantika.ecommerceapp1.entity.User;
import jakarta.persistence.Query;
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
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public List<User> getAllUser() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from CUSTOMER").list();
        return userList;
    }

    @Transactional
    public User saveUser(User user) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(user);
        System.out.println("User added" + user.getId());
        return user;
    }

    //    public User checkLogin() {
//    	this.sessionFactory.getCurrentSession().
//    }
    @Transactional
    public User getUser(String username, String password) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
        query.setParameter("username", username);

        try {
            User user = (User) query.getSingleResult();
            System.out.println(user.getPassword());
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                return new User();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            User user = new User();
            return user;
        }

    }

    @Transactional
    public boolean userExists(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CUSTOMER where username = :username");
        query.setParameter("username", username);
        return !query.getResultList().isEmpty();
    }

    @Transactional
    public User getUserByUsername(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
        query.setParameter("username", username);

        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
