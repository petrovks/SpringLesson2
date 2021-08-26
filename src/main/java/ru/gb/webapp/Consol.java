package ru.gb.webapp;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Consol {
    @Autowired
    CartService cartService;

    public void write(String text) {
        System.out.print(text);
    }

    public String read(Scanner scanner) {
        return scanner.nextLine();
    }

    public void consolCommand() {
        
    }

    public void comToAddCart(Long id) {
        cartService.addToCart(id);
    }
}
