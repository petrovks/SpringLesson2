package ru.gb.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.webapp.model.Product;
import ru.gb.webapp.model.User;
import ru.gb.webapp.services.UserService;

import javax.annotation.PostConstruct;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/show_all")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("showProductsByUserId/{id}")
    public String showProductsByUserId(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("products", userService.findProductsByUserId(id));
        return "user_with_products";
    }

    @GetMapping("users/create")
    public String createUserForm() {
        return "create_user_form";
    }

    @PostMapping("create_new_user")
    public String saveUser(@RequestParam String login) {
        userService.saveUser(new User(userService.getNewId(), login));
        return "redirect:users/show_all";
    }
}
