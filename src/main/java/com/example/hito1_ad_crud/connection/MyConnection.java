package com.example.hito1_ad_crud.connection;

import com.example.hito1_ad_crud.entity.Libro;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyConnection {

    private final Libro libro;

    public MyConnection(Libro libro) {
        this.libro = libro;
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

    public List<Libro> listLibros(ResultSet rs) {
        List<Libro> listaLibros = new ArrayList<>();
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
                listaLibros.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Imposible listar los libros");
        }
        return listaLibros;
    }


    public void insertLibro(ResultSet rs, Libro libro) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Libro updateLibroById(ResultSet rs, Integer idLibro, Libro libro) {
        boolean idEncontrado = false;

        try {
            rs.beforeFirst();
            while (rs.next() && !idEncontrado) {
                if (rs.getInt("idLibro") == idLibro) {
                    idEncontrado = true;
                    rs.updateString("name", libro.getName());
                    rs.updateString("author", libro.getAuthor());
                    rs.updateString("editorial", libro.getEditorial());
                    rs.updateInt("numPages", libro.getNum_pages());
                    rs.updateBoolean("disponible", libro.isDisponible());
                    rs.updateInt("idUser", libro.getIdUser());
                    rs.updateRow();
                    System.out.println("Libro con id " + idLibro + " ha sido actualizado");
                    listById(rs,idLibro);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return libro;
    }

    public Libro listById(ResultSet rs, Integer idLibro) {
        boolean idEncontrado = false;

        try {
            rs.beforeFirst();
            while (rs.next() && !idEncontrado) {
                if (rs.getInt("idLibro") == idLibro) {
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

    public void deleteById(ResultSet rs, Integer idLibro) {

        boolean idEncontrado = false;

        try {
            rs.beforeFirst();
            while (rs.next() && !idEncontrado) {
                if (rs.getInt("idLibro") == idLibro) {
                    idEncontrado = true;
                    rs.deleteRow();
                    System.out.println("Libro con id "+idLibro+" ha sido eliminado");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
