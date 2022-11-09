package com.victorsgdev.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class User {
    @CsvBindByName
    private int idUser;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String nif;

}
