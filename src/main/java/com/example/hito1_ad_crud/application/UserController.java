package com.example.hito1_ad_crud.application;

import com.example.hito1_ad_crud.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController implements Controller{

    private final UserService userService;
    private String table = "User";

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Override
    @GetMapping
    public List<Object> listAll() {
        return userService.listAll(table);
    }

    @Override
    @GetMapping("{id}")
    public Object listById(@PathVariable("id") Integer idUser) {
        return userService.listById(idUser);
    }

    @Override
    @PostMapping
    public Object save(Object object) {
        return userService.save(object);
    }

    @Override
    @PutMapping("{id}")
    public Object updateById(@PathVariable("id") Integer idUser, @RequestBody Object object) {
        return userService.updateById(idUser,object);
    }

    @Override
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Integer idUser) {
        userService.deleteById(idUser);
    }


}
