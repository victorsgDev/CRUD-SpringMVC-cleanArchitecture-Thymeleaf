package com.example.hito1_ad_crud.application;

import com.example.hito1_ad_crud.domain.Libro;
import com.example.hito1_ad_crud.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/libros")
public class LibroController implements Controller {

    private final LibroService libroService;
    private String table = "LIBRO";

    //DI libroService
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @Override
    @GetMapping
    public List<Object> listAll() {
        return libroService.listAll(table);
    }

    @Override
    @GetMapping("/{id}")
    public Object listById(@PathVariable("id") Integer idLibro) {
        return libroService.listById(idLibro);
    }

    @Override
    @PostMapping
    public Object save(Object libro) {
        return libroService.save(libro);
    }

    @Override
    @PutMapping("{id}")
    public Object updateById(@PathVariable("id") Integer idLibro, @RequestBody Object libro) {
        return libroService.updateById(idLibro, libro);
    }

    @Override
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Integer idLibro) {
        libroService.deleteById(idLibro);
    }


}
