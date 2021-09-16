package ru.gb.webapp.repositories;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.gb.webapp.dao.ProductDao;
import ru.gb.webapp.model.Product;

import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;
     private ProductDao productDao;
     private SessionFactory sessionFactory;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findById(Long id) {
        return productDao.findById(id);
    }

    public Product findByTitle(String title) {
        return productDao.findByTitle(title);
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(productDao.findAll());
    }

    public void saveProduct(Product product) {
        productDao.save(product);
    }

    public void updateProduct(Product product) {
        productDao.saveOrUpdate(product);
    }

    public Long getNewId() {
        return productDao.getNewId();
        // return products.stream().mapToLong(Product::getId).max().getAsLong() + 1;
    }

    public void deleteById(Long id) {
        productDao.delete(id);
    }

    public void delete(Product product) {
        productDao.delete(product);
    }

}
