package com.example.hito1_ad_crud.service;

import com.example.hito1_ad_crud.entity.Libro;
import com.example.hito1_ad_crud.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements LibroRepository {

    private final LibroRepository libroRepository;

    public UserService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }


    @Override
    public List<Libro> listAll() {
        return null;
    }

    @Override
    public Libro save(Libro object) {
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
}
