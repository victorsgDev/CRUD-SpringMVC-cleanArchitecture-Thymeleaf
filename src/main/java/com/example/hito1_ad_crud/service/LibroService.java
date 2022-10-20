package com.example.hito1_ad_crud.service;

import com.example.hito1_ad_crud.infrastructure.MyConnection;
import com.example.hito1_ad_crud.domain.Libro;

import java.sql.ResultSet;
import java.util.List;


@org.springframework.stereotype.Repository
public class LibroService implements Repository<Libro, Integer> {

    private final MyConnection myConnection;
    private ResultSet rs;

    public LibroService(MyConnection myConnection) {
        this.myConnection = myConnection;
    }


    @Override
    public List<Libro> listAll() {
        rs = connectToLibros();
        return myConnection.listLibros(rs);
    }

    @Override
    public Libro save(Libro libro) {
        rs = connectToLibros();
        myConnection.insertLibro(rs, libro);
        return null;
    }

    @Override
    public Libro updateById(Integer idLibro, Libro libro) {
        rs = connectToLibros();
        return myConnection.updateLibroById(rs,idLibro, libro);
    }

    @Override
    public void deleteById(Integer idLibro) {
        rs = connectToLibros();
        myConnection.deleteById(rs, idLibro);
    }

    @Override
    public Libro listById(Integer idlibro) {
        rs = connectToLibros();
        return myConnection.listById(rs,idlibro);
    }

    public ResultSet connectToLibros() {
        this.rs = myConnection.connect("jdbc:mysql://localhost:3306/BIBLIOTECA", "LIBRO");
        return rs;
    }
}
