package com.example.hito1_ad_crud.application;

import java.util.List;

public interface Controller {

    List<Object> listAll();

    Object listById(Integer idLibro);

    Object save(Object object);

    Object updateById(Integer idObject, Object object);

    void deleteById(Integer idLibro);

}
