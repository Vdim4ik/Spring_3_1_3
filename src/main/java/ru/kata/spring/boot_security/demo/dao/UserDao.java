package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {

    List<User> getUserList();

    void addUser(User user);

    void updateUser(long id, User updateUser);

    void deleteUser(long id);

    User getUserById(long id);

    User getUserByUsername(String username);

}
