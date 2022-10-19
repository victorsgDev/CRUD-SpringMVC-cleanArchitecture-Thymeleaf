package com.example.hito1_ad_crud.repository;

import com.example.hito1_ad_crud.connection.MyConnection;
import com.example.hito1_ad_crud.entity.Libro;

import java.sql.ResultSet;
import java.util.List;


@org.springframework.stereotype.Repository
public class LibroRepository implements Repository<Libro, Integer> {

    private final MyConnection myConnection;
    private ResultSet rs;

    public LibroRepository(MyConnection myConnection) {
        this.myConnection = myConnection;
    }


    @Override
    public List<Libro> listAll() {
        rs = connectToLibros();
        myConnection.listLibros(rs);
        return null;
    }

    @Override
    public Libro save(Libro libro) {
        rs = connectToLibros();
        myConnection.insertLibro(rs, libro);
        return null;
    }

    @Override
    public Libro updateById(Integer idObject) {
        return null;
    }

    @Override
    public Libro deleteById(Integer idObject) {
        return null;
    }

    @Override
    public Libro listById(Integer idObject) {
        return null;
    }

    public ResultSet connectToLibros() {
        this.rs = myConnection.connect("jdbc:mysql://localhost:3306/BIBLIOTECA", "LIBRO");
        return rs;
    }
}
