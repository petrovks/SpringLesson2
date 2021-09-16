package ru.gb.webapp.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.webapp.dao.ProductDao;
import ru.gb.webapp.model.Product;
import ru.gb.webapp.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private DatabaseService databaseService;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setProductDao(ProductDao productDao) {
        productRepository.setProductDao(productDao);
    }

    public void setFactory(SessionFactory factory) {
        productRepository.setSessionFactory(factory);
    }

    public int getSumPrice() {
        return productRepository.findAll().stream().mapToInt(Product::getCost).sum();
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public Product findByTitle(String title) { return productRepository.findByTitle(title); }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) { productRepository.saveProduct(product);}

    public Long getNewId() { return productRepository.getNewId(); }

    public int incrementCostById(Long id) {
        Product product = productRepository.findById(id);
        product.setCost(product.getCost() + 1);
        productRepository.updateProduct(product);
        return product.getCost();
    }

    public int decrementCostById(Long id) {
        Product product = productRepository.findById(id);
        product.setCost(product.getCost() - 1);
        productRepository.updateProduct(product);
        return product.getCost();
    }
}
