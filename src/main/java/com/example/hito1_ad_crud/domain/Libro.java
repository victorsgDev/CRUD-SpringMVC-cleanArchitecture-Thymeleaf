package com.example.hito1_ad_crud.domain;

import com.opencsv.bean.CsvBindByName;
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

    @CsvBindByName
    private Integer idLibro;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String author;
    @CsvBindByName
    private String editorial;
    @CsvBindByName(column = "numPages")
    private int num_pages;
    @CsvBindByName
    private boolean disponible;
    private int idUser;

}
