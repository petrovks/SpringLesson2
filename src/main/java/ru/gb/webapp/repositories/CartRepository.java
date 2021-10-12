package ru.gb.webapp.repositories;

import org.springframework.stereotype.Component;
import ru.gb.webapp.model.Cart;
import ru.gb.webapp.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class CartRepository {
    private Cart cart;
    private List<Product> productList;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    //добавление по id
    public void addToCart(Product product) {
        if(cart.getProductList() == null) productList = new ArrayList<>();
        else productList = cart.getProductList();
        productList.add(product);
        cart.setProductList(productList);
    }
    //удаление по id
    public void deleteFromCart(Product product) {
        if(cart.getProductList() == null) return;
        productList = cart.getProductList();
        productList.remove(product);
        cart.setProductList(productList);
    }

    public void cartInfo() {
        productList = cart.getProductList();
        productList.stream().map(Product::toString).forEach(System.out::println);
    }

    public List<Product> getProductList() { return productList; }
}
