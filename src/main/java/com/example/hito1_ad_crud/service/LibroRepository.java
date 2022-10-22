package com.example.hito1_ad_crud.service;

import com.example.hito1_ad_crud.domain.Libro;
import com.example.hito1_ad_crud.infrastructure.EntityManager;

import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Repository
public class LibroRepository implements Repository<Libro, Integer> {

    // DI EntityManager
    private final EntityManager entityManager;
    private String cadena_conexion = "jdbc:mysql://localhost:3306/BIBLIOTECA";
    private String table = "LIBRO";

    public LibroRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Libro> listAll(String tabla) {
        connect();
        var res = entityManager.listAll("Libro");
        return res.stream()
                .map(Libro.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Libro listById(Integer idObject) {
        connect();
        return (Libro) entityManager.listById(idObject);
    }

    @Override
    public Libro save(Libro object) {
        connect();
        return (Libro) entityManager.save(object);
    }

    @Override
    public Libro updateById(Integer idObject, Libro object) {
        connect();
        return (Libro) entityManager.updateById(idObject,object);
    }

    @Override
    public void deleteById(Integer idObject) {
        connect();
        entityManager.deleteById(idObject);
    }

    public void connect(){
        entityManager.connect(cadena_conexion,table);
    }


}
