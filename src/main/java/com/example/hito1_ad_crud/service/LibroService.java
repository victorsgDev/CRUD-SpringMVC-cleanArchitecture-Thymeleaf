package com.example.hito1_ad_crud.service;

import com.example.hito1_ad_crud.infrastructure.MyConnection;
import com.example.hito1_ad_crud.domain.Libro;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LibroService extends LibroRepository {

    private final LibroRepository libroRepository;
    private final MyConnection myConnection;

    public LibroService(LibroRepository libroRepository, MyConnection myConnection) {
        super(myConnection);
        this.libroRepository = libroRepository;
        this.myConnection = myConnection;
    }

    @Override
    public List<Libro> listAll(String tabla) {
        return libroRepository.listAll(tabla);
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }



}
