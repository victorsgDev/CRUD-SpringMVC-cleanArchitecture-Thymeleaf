package com.example.hito1_ad_crud.service;

import com.example.hito1_ad_crud.domain.User;

import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
public class UserService implements Service{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Object> listAll(String tabla) {
        return Collections.singletonList(userRepository.listAll(tabla));
    }

    @Override
    public Object save(Object object) {
        return userRepository.save((User) object);
    }

    @Override
    public Object updateById(Integer idObject, Object object) {
        return userRepository.updateById(idObject, (User) object);
    }

    @Override
    public void deleteById(Integer idObject) {
        userRepository.deleteById(idObject);
    }

    @Override
    public Object listById(Integer idObject) {
        return userRepository.listById(idObject);
    }
}
