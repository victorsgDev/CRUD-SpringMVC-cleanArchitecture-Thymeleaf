package com.example.hito1_ad_crud.application;

import com.example.hito1_ad_crud.domain.Libro;
import com.example.hito1_ad_crud.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;
    private String table = "LIBRO";

    //DI libroService
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Object> listAll() {
        return libroService.listAll(table);
    }

    @GetMapping("/{id}")
    public Object listById(@PathVariable("id") Integer idLibro) {
        return libroService.listById(idLibro);
    }

    @PostMapping
    public Libro save(@RequestBody Libro libro) {
        return libroService.save(libro);
    }


    @PutMapping("{id}")
    public Libro updateById(@PathVariable("id") Integer idLibro, @RequestBody Libro libro) {
        return (Libro) libroService.updateById(idLibro, libro);
    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Integer idLibro) {
        libroService.deleteById(idLibro);
    }


}
