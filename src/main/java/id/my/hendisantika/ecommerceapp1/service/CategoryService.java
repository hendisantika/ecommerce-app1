package id.my.hendisantika.ecommerceapp1.service;

import id.my.hendisantika.ecommerceapp1.entity.Category;
import id.my.hendisantika.ecommerceapp1.repository.CategoryDao;
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
 * Time: 05.39
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public Category addCategory(String name) {
        return this.categoryDao.addCategory(name);
    }

    public List<Category> getCategories() {
        return this.categoryDao.getCategories();
    }

    public Boolean deleteCategory(int id) {
        return this.categoryDao.deletCategory(id);
    }

    public Category updateCategory(int id, String name) {
        return this.categoryDao.updateCategory(id, name);
    }

    public Category getCategory(int id) {
        return this.categoryDao.getCategory(id);
    }
}
