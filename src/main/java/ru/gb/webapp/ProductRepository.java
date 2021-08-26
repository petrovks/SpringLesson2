package ru.gb.webapp;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Гречка", 70),
                new Product(2L, "Манка", 65),
                new Product(3L, "Макароны", 50),
                new Product(4L, "Рис", 60),
                new Product(5L, "Пиво", 70)
        ));
    }

    public Product findById(Long id) {
        return products.stream().filter(i -> i.getId().equals(id)).findFirst().get();
    }

    public Product findByTitle(String title) {
        return products.stream().filter(i -> i.getTitle().equals(title)).findFirst().get();
    }

    public List<Product> findAll() {
        return products;
    }

    public void saveProduct(String title, int cost) {
        Long newId = products.stream().mapToLong(Product::getId).max().getAsLong() + 1;
        Product product = new Product(newId, title, cost);
        products.add(product);
    }
}
