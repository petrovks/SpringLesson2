package ru.gb.webapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.webapp.model.Product;
import ru.gb.webapp.services.ProductService;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/show_all")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/show/{id}")
    public String showProductById(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.findById(id));
        return "product_info";
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "create_new_product";
    }

    @PostMapping("/create")
    public String saveProduct(@RequestParam String title, @RequestParam int cost) {
        Product product = new Product(productService.getNewId(),title, cost);
        productService.addProduct(product);
        return "redirect:/show_all";
    }

    @GetMapping("/product/{id}/incrementCost")
    public String incrementCostById(Model model, @PathVariable Long id){
        model.addAttribute("product", productService.incrementCostById(id));
        return "redirect:/show_all";
    }

    @GetMapping("/product/{id}/decrementCost")
    public String decrementCostById(Model model, @PathVariable Long id){
        model.addAttribute("product", productService.decrementCostById(id));
        return "redirect:/show_all";
    }
}
