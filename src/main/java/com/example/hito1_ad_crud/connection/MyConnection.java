package com.example.hito1_ad_crud.connection;

import com.example.hito1_ad_crud.entity.Libro;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@Data
public class MyConnection {

    public ResultSet connect(String cadenaConexion, String table) {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cadenaConexion, "root", "root");
            System.out.println("Conectado con exito a la BD");
            return executeQuery(con, table);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Imposible acceder a la base de datos");
        }
        return null;

    }

    public ResultSet executeQuery(Connection con, String table) {
        try {
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            if (table.equals("LIBRO")) {
                ResultSet rs = statement.executeQuery("SELECT * FROM LIBRO");
                return rs;
            } else if (table.equals("USER")) {
                ResultSet rs = statement.executeQuery("SELECT * FROM USER");
                return rs;
            } else {
                System.out.println(table);
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listLibros(ResultSet rs) {
        try {
            rs.beforeFirst();
            while (rs.next()) {
                System.out.print(rs.getInt("idLibro"));
                System.out.print(" - ");
                System.out.print(rs.getString("name"));
                System.out.print(" - ");
                System.out.print(rs.getString("author"));
                System.out.print(" - ");
                System.out.print(rs.getString("editorial"));
                System.out.print(" - ");
                System.out.print(rs.getInt("numPages"));
                System.out.print(" - ");
                System.out.print(rs.getBoolean("disponible"));
                System.out.print(" - ");
                System.out.print(rs.getBoolean("idUser"));
                System.out.print(" - ");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Imposible listar los libros");
        }

    }


    public void insertLibro(ResultSet rs, Libro libro) {
        try {
            rs.moveToInsertRow();

            rs.insertRow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
