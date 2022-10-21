package com.example.hito1_ad_crud.infrastructure;

import com.example.hito1_ad_crud.domain.Libro;
import com.example.hito1_ad_crud.domain.User;
import com.example.hito1_ad_crud.service.Repository;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MyConnection implements Repository<Object, Integer> {

    private final Libro libro;
    private final User user;
    private ResultSet rs;

    public MyConnection(Libro libro, User user) {
        this.libro = libro;
        this.user = user;
    }

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
                this.rs = statement.executeQuery("SELECT * FROM LIBRO");
                return rs;
            } else if (table.equals("USER")) {
                this.rs = statement.executeQuery("SELECT * FROM USER");
                return rs;
            } else {
                System.out.println(table);
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object> listAll(String tabla) {
        List<Object> lista;
        if (tabla.equals("Libro")) {
            lista = new ArrayList<>();
            try {
                rs.beforeFirst();
                while (rs.next()) {
                    libro.setIdLibro(rs.getInt("idLibro"));
                    libro.setName(rs.getString("name"));
                    libro.setAuthor(rs.getString("author"));
                    libro.setEditorial(rs.getString("editorial"));
                    libro.setNum_pages(rs.getInt("numPages"));
                    libro.setDisponible(rs.getBoolean("disponible"));
                    libro.setIdUser(rs.getInt("idUser"));
                    lista.add(libro);
                }
            } catch (SQLException e) {
                System.out.println("Imposible listar los libros");
            }
        } else if (tabla.equals(user.getClass().getName())) {
            lista = null;
        } else {
            lista = null;
        }
        return lista;
    }

    @Override
    public Object save(Object object) {
        if (object.getClass() == Libro.class) {
            try {
                rs.moveToInsertRow();
                rs.updateInt("idLibro", libro.getIdLibro());
                rs.updateString("name", libro.getName());
                rs.updateString("author", libro.getAuthor());
                rs.updateString("editorial", libro.getEditorial());
                rs.updateInt("numPages", libro.getNum_pages());
                rs.updateBoolean("disponible", libro.isDisponible());
                rs.updateInt("idUser", libro.getIdUser());
                rs.insertRow();
                return libro;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (object.getClass() == User.class) {
            // TODO PENDIENTE...!!!!!!!!!!!!!!!!!!
            try {
                rs.moveToInsertRow();
                rs.updateInt("idLibro", user.getIdUser());
                rs.updateString("name", libro.getName());
                rs.updateString("author", libro.getAuthor());
                rs.updateString("editorial", libro.getEditorial());
                rs.updateInt("numPages", libro.getNum_pages());
                rs.updateBoolean("disponible", libro.isDisponible());
                rs.updateInt("idUser", libro.getIdUser());
                rs.insertRow();
                return user;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Object updateById(Integer idObject, Object object) {
        boolean idEncontrado = false;
        try {
            libro.setIdLibro((Integer) idObject);
            rs.beforeFirst();
            while (rs.next() && !idEncontrado) {
                if (rs.getInt("idLibro") == idObject) {
                    idEncontrado = true;
                    rs.updateString("name", libro.getName());
                    rs.updateString("author", libro.getAuthor());
                    rs.updateString("editorial", libro.getEditorial());
                    rs.updateInt("numPages", libro.getNum_pages());
                    rs.updateBoolean("disponible", libro.isDisponible());
                    rs.updateInt("idUser", libro.getIdUser());
                    rs.updateRow();
                    System.out.println("Libro con id " + idObject + " ha sido actualizado");
                    listById(idObject);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return libro;
    }

    @Override
    public void deleteById(Integer idObject) {

        boolean idEncontrado = false;

        try {
            rs.beforeFirst();
            while (rs.next() && !idEncontrado) {
                libro.setIdLibro(idObject);
                if (rs.getInt("idLibro") == libro.getIdLibro()) {
                    idEncontrado = true;
                    rs.deleteRow();
                    System.out.println("Libro con id " + idObject + " ha sido eliminado");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object listById(Integer idObject) {
        boolean idEncontrado = false;

        try {
            rs.beforeFirst();
            while (rs.next() && !idEncontrado) {
                libro.setIdLibro(idObject);
                if (rs.getInt("idLibro") == libro.getIdLibro()) {
                    idEncontrado = true;

                    libro.setIdLibro(rs.getInt("idLibro"));
                    libro.setName(rs.getString("name"));
                    libro.setAuthor(rs.getString("author"));
                    libro.setEditorial(rs.getString("editorial"));
                    libro.setNum_pages(rs.getInt("numPages"));
                    libro.setDisponible(rs.getBoolean("disponible"));
                    libro.setIdUser(rs.getInt("idUser"));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libro;
    }


}
