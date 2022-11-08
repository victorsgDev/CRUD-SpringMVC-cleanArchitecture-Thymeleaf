package com.example.hito1_ad_crud.application;

import com.example.hito1_ad_crud.domain.Libro;
import com.example.hito1_ad_crud.service.LibroService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
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

    //  TODO CSV:
    @GetMapping("/libros/csv")
    public String index() {
        return "libros_csv";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Libro> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Libro.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<Libro> libros = csvToBean.parse();

                // TODO: save users in DB?

                // save users list on model
                model.addAttribute("libros", libros);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "file-upload-status";
    }



}
