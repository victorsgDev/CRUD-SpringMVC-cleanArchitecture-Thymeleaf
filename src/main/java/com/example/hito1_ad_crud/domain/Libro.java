package com.example.hito1_ad_crud.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro{

    private Integer idLibro;
    private String name;
    private String author;
    private String editorial;
    private int num_pages;
    private boolean disponible;
    private int idUser;

}
