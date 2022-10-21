package com.example.hito1_ad_crud.service;

import com.example.hito1_ad_crud.infrastructure.MyConnection;
import com.example.hito1_ad_crud.domain.Libro;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Repository
public class LibroRepository implements Repository<Libro, Integer> {

    private final MyConnection myConnection;
    // private ResultSet rs = connectToLibros();

    public LibroRepository(MyConnection myConnection) {
        this.myConnection = myConnection;
    }


    @Override
    public List<Libro> listAll(String tabla) {
        myConnection.connect("jdbc:mysql://localhost:3306/BIBLIOTECA", "LIBRO");
        var res = myConnection.listAll("Libro");
        return res.stream()
                .map(Libro.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Libro save(Libro object) {
        return null;
    }

    @Override
    public Libro updateById(Integer idObject, Libro object) {
        return null;
    }

    @Override
    public void deleteById(Integer idObject) {

    }

    @Override
    public Libro listById(Integer idObject) {
        return null;
    }

}
