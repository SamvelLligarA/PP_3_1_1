package org.example.pp_3_1_1.dao;

import org.example.pp_3_1_1.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getUsers();
    void addUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    Optional<User> findById(Long id);

}

