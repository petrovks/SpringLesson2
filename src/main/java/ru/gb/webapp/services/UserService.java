package ru.gb.webapp.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.webapp.dao.UserDao;
import ru.gb.webapp.model.Product;
import ru.gb.webapp.model.User;
import ru.gb.webapp.repositories.UserRepository;

import java.util.List;
@Service
public class UserService {
    private UserRepository userRepository;
   // private DatabaseService databaseService;
    @Autowired
    public UserService(UserRepository userRepository/*, DatabaseService databaseService*/) {
        this.userRepository = userRepository;
      //  this.databaseService = databaseService;
    }

    public void setUserDao(UserDao userDao) {
        userRepository.setUserDao(userDao);
    }

    public void setFactory(SessionFactory factory) {
        userRepository.setFactory(factory);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return  userRepository.findAll();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteUser(id);
    }

    public Long getNewId() {
        return userRepository.getNewId();
    }

    public List<Product> findProductsByUserId(Long id) {
        return userRepository.findProductsByUserId(id);
    }
}
