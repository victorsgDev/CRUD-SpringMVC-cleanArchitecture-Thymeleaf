package com.victorsgdev.application;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.victorsgdev.domain.Libro;
import com.victorsgdev.service.LibroService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
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

    @GetMapping("/libros/to-csv")
    public void getAllLibrosInCsv(HttpServletResponse servletResponse) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {

        String filename = "libros.csv";

        servletResponse.setContentType("text/csv");
        servletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");


        StatefulBeanToCsv<Libro> writer = new StatefulBeanToCsvBuilder<Libro>(servletResponse.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        List<Libro> listLibros = new ArrayList<>();
        var listObject = libroService.listAll(table);
        var rs = listObject.stream().map(List.class::cast);
        rs.forEach(list -> list.forEach(o -> listLibros.add((Libro) o)));

        writer.write(listLibros);
    }

    @PostMapping("/libros/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file) {

            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                CsvToBean<Libro> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Libro.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<Libro> libros = csvToBean.parse();

                libros.forEach(libroService::save);
                return "redirect:/libros";


            } catch (Exception ex) {
                System.out.println("No ha sido posible importar");
            }

        return "libros";
    }


}
