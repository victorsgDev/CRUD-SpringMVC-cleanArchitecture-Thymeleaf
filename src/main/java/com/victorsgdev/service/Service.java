package com.victorsgdev.service;

import java.util.List;

public interface Service {

    List<Object> listAll(String tabla);

    Object save(Object object);

    Object updateById(Integer idObject, Object object);

    void deleteById(Integer idObject);

    Object listById(Integer idObject);

}
