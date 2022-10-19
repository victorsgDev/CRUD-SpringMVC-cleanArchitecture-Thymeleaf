package com.example.hito1_ad_crud.controller;

import com.example.hito1_ad_crud.entity.Libro;
import com.example.hito1_ad_crud.service.LibroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/libros")
    public List<Libro> listAll(){
        return libroService.listAll();
    }

    @PostMapping("libros")
    public Libro save(@RequestBody Libro libro){
        return libroService.save(libro);
    }



}
