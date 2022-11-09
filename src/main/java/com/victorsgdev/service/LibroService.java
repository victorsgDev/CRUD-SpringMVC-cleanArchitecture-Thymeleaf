package com.victorsgdev.service;

import com.victorsgdev.domain.Libro;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LibroService implements com.victorsgdev.service.Service {

    // DI LibroRepository
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Object> listAll(String tabla) {
        return Collections.singletonList(libroRepository.listAll(tabla));
    }

    @Override
    public Object listById(Integer idObject) {
        return libroRepository.listById(idObject);
    }

    @Override
    public Libro save(Object libro) {
        return libroRepository.save((Libro) libro);
    }


    @Override
    public Object updateById(Integer idObject, Object object) {
        return libroRepository.updateById(idObject, (Libro) object);
    }

    @Override
    public void deleteById(Integer idObject) {
        libroRepository.deleteById(idObject);
    }

}


