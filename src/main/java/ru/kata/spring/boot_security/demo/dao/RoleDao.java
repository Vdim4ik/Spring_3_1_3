package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRoleList();

    void addRole(Role role);

    void updateRole(long id, Role updateRole);

    void deleteRole(long id);

    Role getRoleByName(String name);
}
