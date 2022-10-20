package com.example.hito1_ad_crud.controller;

import com.example.hito1_ad_crud.entity.Libro;
import com.example.hito1_ad_crud.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping()
    public List<Libro> listAll(){
        return libroService.listAll();
    }
    @GetMapping("/{id}")
    public Libro listById(@PathVariable("id") Integer idLibro){
        return libroService.listById(idLibro);
    }

    @PostMapping()
    public Libro save(@RequestBody Libro libro){
        return libroService.save(libro);
    }

    @PutMapping("{id}")
    public Libro updateById(@PathVariable("id") Integer idLibro, @RequestBody Libro libro){
        return libroService.updateById(idLibro, libro);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Integer idLibro){
        libroService.deleteById(idLibro);
    }

}
