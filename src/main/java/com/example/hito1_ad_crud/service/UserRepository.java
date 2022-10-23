package com.example.hito1_ad_crud.service;

import com.example.hito1_ad_crud.domain.Libro;
import com.example.hito1_ad_crud.domain.User;
import com.example.hito1_ad_crud.infrastructure.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User, Integer> {

    // DI EntityManager
    private final EntityManager entityManager;
    private String cadena_conexion = "jdbc:mysql://localhost:3306/BIBLIOTECA";
    private String table = "USER";


    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> listAll(String tabla) {
        connect();
        var res = entityManager.listAll("User");
        return res.stream()
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public User save(User object) {
        connect();
        return (User) entityManager.save(object);
    }

    @Override
    public User updateById(Integer idObject, User object) {
        connect();
        return (User) entityManager.updateById(idObject, object);
    }

    @Override
    public void deleteById(Integer idObject) {
        connect();
        entityManager.deleteById(idObject);
    }

    @Override
    public User listById(Integer idObject) {
        connect();
        return (User) entityManager.listById(idObject);
    }

    public void connect() {
        entityManager.connect(cadena_conexion, table);
    }
}
