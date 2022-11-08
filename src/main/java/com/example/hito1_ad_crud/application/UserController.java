package com.example.hito1_ad_crud.application;


import com.example.hito1_ad_crud.domain.User;
import com.example.hito1_ad_crud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

}
