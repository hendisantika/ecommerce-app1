package id.my.hendisantika.ecommerceapp1.repository;

import id.my.hendisantika.ecommerceapp1.entity.Category;
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
 * Time: 05.36
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public Category addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        this.sessionFactory.getCurrentSession().saveOrUpdate(category);
        return category;
    }

    @Transactional
    public List<Category> getCategories() {
        return this.sessionFactory.getCurrentSession().createQuery("from CATEGORY").list();
    }

    @Transactional
    public Boolean deletCategory(int id) {

        Session session = this.sessionFactory.getCurrentSession();
        Object persistanceInstance = session.load(Category.class, id);

        if (persistanceInstance != null) {
            session.delete(persistanceInstance);
            return true;
        }
        return false;
    }

    @Transactional
    public Category updateCategory(int id, String name) {
        Category category = this.sessionFactory.getCurrentSession().get(Category.class, id);
        category.setName(name);

        this.sessionFactory.getCurrentSession().update(category);
        return category;
    }

    @Transactional
    public Category getCategory(int id) {
        return this.sessionFactory.getCurrentSession().get(Category.class, id);
    }
}
