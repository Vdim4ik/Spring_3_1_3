package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoleList() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(long id, Role updateRole) {
        Role role = entityManager.find(Role.class, id);
        role.setName(updateRole.getName());
    }

    @Override
    public void deleteRole(long id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select r from Role r where r.name =: name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
