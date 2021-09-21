package ru.gb.webapp.dao;

import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.gb.webapp.model.Product;
import ru.gb.webapp.model.User;

import java.util.List;

public class UserDao {
    private SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }

    public User findById(Long id) {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }
    }

    public User findByLogin(String login) {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, login);
            session.getTransaction().commit();
            return user;
        }
    }

    public List<User> findAll() {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<User> users = (List<User>) session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    public User saveOrUpdate(User user) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            return user;
        }
    }

    public User save(User user) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return user;
        }
    }

    public void delete(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from User u where u.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Long getNewId() {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.createQuery("select u from User u where id = (select max(id) from User )", User.class).getSingleResult();
            Long id = user.getId() + 1;
            session.getTransaction().commit();
            return id;
        }
    }

    public List<Product> findProductsByUserId(Long id) {
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            User userFromNamedQuery = session
                    .createNamedQuery("withProducts", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
            Product u = session.get(Product.class, id);
            session.getTransaction().commit();
            return userFromNamedQuery.getProducts();
        }
    }
}
