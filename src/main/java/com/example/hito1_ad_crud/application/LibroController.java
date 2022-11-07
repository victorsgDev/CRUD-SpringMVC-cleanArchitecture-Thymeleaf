package com.example.hito1_ad_crud.application;

import com.example.hito1_ad_crud.domain.Libro;
import com.example.hito1_ad_crud.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping()
public class LibroController {

    private final LibroService libroService;
    private final Libro libro;
    private String table = "LIBRO";

    //DI libroService
    public LibroController(LibroService libroService, Libro libro) {
        this.libroService = libroService;
        this.libro = libro;
    }

    @GetMapping("/libros")
    public String listAll(Model model) {
        List<Libro> listLibros = new ArrayList<>();
        var listObject = libroService.listAll(table);
        var rs = listObject.stream().map(List.class::cast);
        rs.forEach(list -> list.forEach(o -> listLibros.add((Libro) o)));

        model.addAttribute("libros",
                listLibros);
        return "libros";
    }

    @GetMapping("/{id}")
    public Object listById(@PathVariable("id") Integer idLibro) {
        return libroService.listById(idLibro);
    }

    @GetMapping("/libros/create")
    public String newLibro(Model model){
        model.addAttribute("libro", libro);
        return "create_libro";
    }

    @PostMapping("/libros")
    public String save(@ModelAttribute("libro") Libro libro) {
        libroService.save(libro);
        return "redirect:/libros";
    }

    @GetMapping("/libros/editar/{id}")
    public String goToEditLibro(@PathVariable Integer id, Model modelo) {
        var libro = (Libro) libroService.listById(id);
        modelo.addAttribute("libro",  libro);
        return "libro_update";
    }



    @PostMapping("/libros/{id}")
    public String actualizarEstudiante(@PathVariable Integer id, @ModelAttribute("libro") Libro libro) {
        libroService.updateById(id,libro);
        return "redirect:/libros";
    }

    @GetMapping("/libros/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Integer id) {
        libroService.deleteById(id);
        return "redirect:/libros";
    }



}
