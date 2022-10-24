package com.example.hito1_ad_crud.application;

import com.example.hito1_ad_crud.domain.User;
import com.example.hito1_ad_crud.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final String table = "User";

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping
    public List<Object> listAll() {
        return userService.listAll(table);
    }


    @GetMapping("{id}")
    public Object listById(@PathVariable("id") Integer idUser) {
        return userService.listById(idUser);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return (User) userService.save(user);
    }


    @PutMapping("{id}")
    public User updateById(@PathVariable("id") Integer idUser, @RequestBody User user) {
        return (User) userService.updateById(idUser, user);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Integer idUser) {
        userService.deleteById(idUser);
    }


}
