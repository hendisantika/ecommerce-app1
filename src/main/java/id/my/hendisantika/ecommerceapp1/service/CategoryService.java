package id.my.hendisantika.ecommerceapp1.service;

import id.my.hendisantika.ecommerceapp1.entity.Category;
import id.my.hendisantika.ecommerceapp1.repository.CategoryRepository;
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
 * Time: 05.39
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return this.categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    public Boolean deleteCategory(int id) {
        try {
            this.categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Category updateCategory(int id, String name) {
        Optional<Category> categoryOpt = this.categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            Category category = categoryOpt.get();
            category.setName(name);
            return this.categoryRepository.save(category);
        }
        return null;
    }

    public Category getCategory(int id) {
        Optional<Category> categoryOpt = this.categoryRepository.findById(id);
        return categoryOpt.orElse(null);
    }
}
