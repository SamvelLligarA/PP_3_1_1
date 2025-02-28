package org.example.pp_3_1_1.service;

import org.example.pp_3_1_1.dao.UserDao;
import org.example.pp_3_1_1.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findById(Long id) {
        logger.debug("Finding user by id: {}", id);
        return userDao.findById(id);
    }

    @Override
    public List<User> getUsers() {
        logger.debug("Getting all users");
        return userDao.getUsers();
    }

    @Override
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        logger.info("Adding new user: {}", user);
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        userDao.deleteUser(id);
    }

    @Override
    public void editUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        logger.info("Editing user: {}", user);
        userDao.editUser(user);
    }
}
