package ru.gb.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public void addToCart(Long id){
        cartRepository.addToCart(productService.findById(id));
    }

    public void deleteFromCart(Long id) {
        cartRepository.deleteFromCart(productService.findById(id));
    }

    public void cartInfo() {
        cartRepository.cartInfo();
    }

    public int getSumCart() {
        return cartRepository.getProductList().stream().mapToInt(Product::getCost).sum();
    }
}
