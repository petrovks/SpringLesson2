package ru.gb.webapp;

import org.hibernate.loader.custom.ScalarResultColumnProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean(ProductService.class);
       // System.out.println(productService.getSumPrice());
        Consol consol = new Consol();
        CartService cartService = context.getBean(CartService.class);
        try (Scanner scanner = new Scanner(System.in)){
            while(true){

                consol.write("Выбериту действие(Введите число): 1. Добавить в корзину, 2. Удалить из корзины, " +
                        "3. Добавить новый продукт в список, 4. Просмотр корзины, для выхода введите exit." + "\n");

                String command = consol.read(scanner);
                switch (command) {
                    case "1" :
                        System.out.println("Выберите продукт из списка: ");
                        productService.findAll().stream().map(Product::getTitle).forEach(x -> System.out.print(x + ", "));
                        System.out.println();
                        command = consol.read(scanner);
                        cartService.addToCart(productService.findByTitle(command).getId());
                        break;
                    case "2" :
                        cartService.cartInfo();
                        System.out.println("Введите название продукта для удаления: ");
                        command = consol.read(scanner);
                        cartService.deleteFromCart(productService.findByTitle(command).getId());
                        break;

                    case "3" :
                        System.out.println("Введите название продукта");
                        String title = consol.read(scanner);
                        System.out.println("Введите стоимость продукта");
                        int cost = Integer.parseInt(consol.read(scanner));
                        productService.addProduct(title, cost);
                        break;
                    case "4" :
                        cartService.cartInfo();
                        System.out.println("Общая сумма: " + cartService.getSumCart());
                        break;
                    default:
                        System.out.println("Вы выбрали неправильное действие");
                        continue;
                }
                if (command.equals("exit")) break;
//                consol.write("Выберите продукт: ");
//                productService.findAll().stream().map(Product::getTitle).forEach(x -> System.out.print(x + ", "));
//                System.out.println();
//                String input = consol.read(scanner);


            }
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Упс!!!");
        }


        //        cartService.addToCart(productService.findByTitle("Гречка").getId());
//        cartService.addToCart(productService.findByTitle("Пиво").getId());
//        cartService.cartInfo();
//        cartService.deleteFromCart(productService.findByTitle("Гречка").getId());
//        System.out.println();
//        cartService.cartInfo();
        context.close();
    }

}
