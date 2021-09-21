package ru.gb.webapp.repositories;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.gb.webapp.dao.UserDao;
import ru.gb.webapp.model.Product;
import ru.gb.webapp.model.User;

import java.util.List;

@Component
public class UserRepository {
    private List<User> users;
    private UserDao userDao;
    private SessionFactory factory;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public void updateUser(User user) {
        userDao.saveOrUpdate(user);
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }

    public Long getNewId() {
        return userDao.getNewId();
    }

    public List<Product> findProductsByUserId(Long id) {
        return  userDao.findProductsByUserId(id);
    }
}
