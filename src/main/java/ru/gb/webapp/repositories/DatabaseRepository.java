package ru.gb.webapp.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
public class DatabaseRepository {
    private SessionFactory factory;

    @PostConstruct
    public void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;

        try {
//            for (Product p: products)
//            {
//                productDao.saveOrUpdate(p);
//            }
            String sql = Files.lines(Paths.get("import.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println("UUUPS!!!");
            e.printStackTrace();
        }
    }

    public SessionFactory getFactory() {
        return factory;
    }
}
