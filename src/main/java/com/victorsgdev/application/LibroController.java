package com.victorsgdev.application;

import com.victorsgdev.domain.Libro;
import com.victorsgdev.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/libros/{id}")
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
    public String updateLibros(@PathVariable Integer id, @ModelAttribute("libro") Libro libro) {
        libroService.updateById(id,libro);
        return "redirect:/libros";
    }


    @GetMapping("/libros/eliminar/{id}")
    public String deleteLibro(@PathVariable Integer id) {
        libroService.deleteById(id);
        return "redirect:/libros";
    }

    @GetMapping("/libros/to-csv")
    public void getAllLibrosInCsv(HttpServletResponse servletResponse) {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"libros.csv\"");
        try {
            libroService.exportToCsv(servletResponse.getWriter(),table);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
