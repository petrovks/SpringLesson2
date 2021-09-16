package ru.gb.webapp.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.webapp.dao.ProductDao;
import ru.gb.webapp.repositories.DatabaseRepository;

import javax.annotation.PostConstruct;

@Service
public class DatabaseService {
    private DatabaseRepository databaseRepository;
    private ProductService productService;

    @Autowired
    public DatabaseService(DatabaseRepository databaseRepository, ProductService productService) {
        this.databaseRepository = databaseRepository;
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        databaseRepository.init();
        productService.setFactory(databaseRepository.getFactory());
        productService.setProductDao(new ProductDao(databaseRepository.getFactory()));
    }

    public SessionFactory getFactory() {
        return databaseRepository.getFactory();
    }
}
