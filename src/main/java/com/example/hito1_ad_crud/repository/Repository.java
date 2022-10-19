package com.example.hito1_ad_crud.repository;

import java.util.List;
@org.springframework.stereotype.Repository
public interface Repository<Object,Integer> {
//TODO LOGICA AQUÍ
    List<Object> listAll();

    Object save(Object object);

    Object updateById(Integer idObject);

    Object deleteById(Integer idObject);

    Object listById(Integer  idObject);
}
