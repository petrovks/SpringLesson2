package ru.gb.webapp.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.webapp.dao.ProductDao;
import ru.gb.webapp.dao.UserDao;
import ru.gb.webapp.repositories.DatabaseRepository;

import javax.annotation.PostConstruct;

@Service
public class DatabaseService {
    private DatabaseRepository databaseRepository;
    private ProductService productService;
    private UserService userService;

    @Autowired
    public DatabaseService(DatabaseRepository databaseRepository, ProductService productService, UserService userService) {
        this.databaseRepository = databaseRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        databaseRepository.init();
        productService.setFactory(databaseRepository.getFactory());
        productService.setProductDao(new ProductDao(databaseRepository.getFactory()));
        userService.setUserDao(new UserDao(databaseRepository.getFactory()));
    }

    public SessionFactory getFactory() {
        return databaseRepository.getFactory();
    }
}
