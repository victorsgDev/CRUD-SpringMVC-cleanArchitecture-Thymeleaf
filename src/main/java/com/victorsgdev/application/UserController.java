package com.victorsgdev.application;


import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.victorsgdev.domain.User;
import com.victorsgdev.service.UserService;
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
public class UserController {

    private final UserService userService;
    private final User user;
    private final String table = "User";

    public UserController(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }


    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> listUsers = new ArrayList<>();
        var listObject = userService.listAll(table);
        var rs = listObject.stream().map(List.class::cast);
        rs.forEach(list -> list.forEach(o -> listUsers.add((User) o)));

        model.addAttribute("users",
                listUsers);
        return "users";
    }


    @GetMapping("/users/{id}")
    public Object listById(@PathVariable("id") Integer idUser) {
        return userService.listById(idUser);
    }

    @GetMapping("/users/create")
    public String newUser(Model model) {
        model.addAttribute("user", user);
        return "create_user";
    }

    @PostMapping("/users")
    public String save(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/editar/{id}")
    public String goToEditUser(@PathVariable Integer id, Model modelo) {
        var user = (User) userService.listById(id);
        modelo.addAttribute("user", user);
        return "user_update";
    }


    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User user) {
        userService.updateById(id, user);
        return "redirect:/users";
    }

    @GetMapping("/users/eliminar/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/users/to-csv")
    public void getAllUsersInCsv(HttpServletResponse servletResponse) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String filename = "users.csv";

        servletResponse.setContentType("text/csv");
        servletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<User> writer = new StatefulBeanToCsvBuilder<User>(servletResponse.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        List<User> listUsers = new ArrayList<>();
        var listObject = userService.listAll(table);
        var rs = listObject.stream().map(List.class::cast);
        rs.forEach(list -> list.forEach(o -> listUsers.add((User) o)));

        writer.write(listUsers);
    }

    @PostMapping("/users/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file) {

        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            CsvToBean<User> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(User.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<User> users = csvToBean.parse();

            users.forEach(userService::save);
            return "redirect:/users";
        } catch (Exception ex) {
            System.out.println("No ha sido posible importar");

        }

        return"users";
}

}
