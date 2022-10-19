package com.example.hito1_ad_crud.service;

import com.example.hito1_ad_crud.connection.MyConnection;
import com.example.hito1_ad_crud.entity.Libro;
import com.example.hito1_ad_crud.repository.LibroRepository;
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
    public List<Libro> listAll() {
        return libroRepository.listAll();
    }

    @Override
    public Libro save(Libro libro) {
        return libroRepository.save(libro);
    }
//
//    @Override
//    public Libro updateById(Integer idObject) {
//        return libroRepository.updateById();
//    }
//
//    @Override
//    public Libro deleteById(Integer idObject) {
//        return libroRepository.deleteById();
//    }
//
//    @Override
//    public Libro listById(Integer idObject) {
//        return libroRepository.listById();
//    }
}
