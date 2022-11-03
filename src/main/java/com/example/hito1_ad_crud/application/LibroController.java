package com.example.hito1_ad_crud.application;

import com.example.hito1_ad_crud.domain.Libro;
import com.example.hito1_ad_crud.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
//@RequestMapping()
public class LibroController {

    private final LibroService libroService;
    private String table = "LIBRO";

    //DI libroService
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/libros")
    public String listAll(Model model) {
        var listObject = libroService.listAll(table);
        List<Libro> res= listObject.stream()
                .map(Libro.class::cast)
                .collect(Collectors.toList());

        model.addAttribute("libros",
                res);
        return "libros";
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
