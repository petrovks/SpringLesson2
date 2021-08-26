package ru.gb.webapp;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

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

    public void addProduct(String title, int cost) { productRepository.saveProduct(title, cost);}
}
