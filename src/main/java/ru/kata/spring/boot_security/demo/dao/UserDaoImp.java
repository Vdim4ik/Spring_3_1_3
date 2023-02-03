package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUserList() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(long id, User updateUser) {
        User user = entityManager.find(User.class, id);
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        user.setAge(updateUser.getAge());
        user.setEmail(updateUser.getEmail());
        user.setRoles(updateUser.getRoles());
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("select u from User u where u.username =: username", User.class).setParameter("username", username).getSingleResult();
    }
}
