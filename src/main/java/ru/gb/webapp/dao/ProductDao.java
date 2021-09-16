package ru.gb.webapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.gb.webapp.model.Product;

import java.util.List;

public class ProductDao {
    private SessionFactory sessionFactory;

    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findById(Long id) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public Product findByTitle(String title) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, title);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = (List<Product>) session.createQuery("from Product").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }

    public Product save(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            return product;
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void delete(Product product) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public Long getNewId() {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.createQuery("select p from Product p where id = (select max(id) from Product )", Product.class).getSingleResult();
            Long id = product.getId() + 1;
            session.getTransaction().commit();
            return id;
        }
    }
}
