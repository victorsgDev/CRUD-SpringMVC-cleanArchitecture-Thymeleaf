package com.example.hito1_ad_crud.connection;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Data
public class MyConnection {

    public void connect(String cadenaConexion){

        try {
            Connection con = DriverManager.getConnection(cadenaConexion,"root","root");
            System.out.println("Conectado con exito a la BD");
        } catch (SQLException e) {
            System.out.println("Imposible acceder a la base de datos");
        }


    }

}
