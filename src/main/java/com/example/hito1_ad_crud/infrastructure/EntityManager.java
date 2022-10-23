package com.example.hito1_ad_crud.infrastructure;

import com.example.hito1_ad_crud.domain.Libro;
import com.example.hito1_ad_crud.domain.User;
import com.example.hito1_ad_crud.service.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntityManager implements Repository<Object, Integer> {

    @Autowired
    private Libro libro;
    @Autowired
    private User user;
    private ResultSet rs;

    private Connection con;

    public ResultSet connect(String cadenaConexion, String table) {
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
                closeConnection();
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
                    // TODO REVISAR NEW :
                    Libro libro = new Libro();

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
        } else if (tabla.equals("User")) {
            lista = new ArrayList<>();
            try {
                rs.beforeFirst();
                while (rs.next()) {
                    // TODO REVISAR NEW :
                    User user = new User();

                    user.setIdUser(rs.getInt("idUser"));
                    user.setName(rs.getString("name"));
                    user.setNif(rs.getString("nif"));
                    lista.add(user);
                }
            } catch (SQLException e) {
                System.out.println("Imposible listar los usuarios");
            }
        } else
            lista = null;

        closeConnection();
        return lista;
    }


    @Override
    public Object listById(Integer idObject) {
        boolean idEncontrado = false;
        if (String.valueOf(idObject).charAt(0) == '2') {
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
            closeConnection();
            return libro;
        } else if (String.valueOf(idObject).charAt(0) == '1') {
            try {
                rs.beforeFirst();
                while (rs.next() && !idEncontrado) {
                    user.setIdUser(idObject);
                    if (rs.getInt("idUser") == user.getIdUser()) {
                        idEncontrado = true;

                        user.setIdUser(rs.getInt("idUser"));
                        user.setName(rs.getString("name"));
                        user.setNif(rs.getString("nif"));

                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            closeConnection();
            return user;
        } else {
            closeConnection();
            return null;
        }
    }

    @Override
    public Object save(Object object) {
        if (object.getClass() == Libro.class) {
            libro = (Libro) object;
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

                closeConnection();
                return libro;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (object.getClass() == User.class) {

            user = (User) object;
            try {
                rs.moveToInsertRow();
                rs.updateInt("idUser", user.getIdUser());
                rs.updateString("name", user.getName());
                rs.updateString("nif", user.getNif());
                rs.insertRow();

                closeConnection();
                return user;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        closeConnection();
        return null;
    }

    @Override
    public Object updateById(Integer idObject, Object object) {
        boolean idEncontrado = false;
        if (String.valueOf(idObject).charAt(0) == '2') {
            try {
                libro = (Libro) object;
                libro.setIdLibro(idObject);
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
            closeConnection();
            return libro;

        } else if (String.valueOf(idObject).charAt(0) == '1') {
            try {
                user = (User) object;
                user.setIdUser(idObject);
                rs.beforeFirst();
                while (rs.next() && !idEncontrado) {
                    if (rs.getInt("idUser") == idObject) {
                        idEncontrado = true;
                        rs.updateString("name", user.getName());
                        rs.updateString("nif", user.getNif());

                        rs.updateRow();
                        System.out.println("User con id " + idObject + " ha sido actualizado");
                        listById(idObject);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            closeConnection();
            return user;
        }

        closeConnection();
        return null;
    }

    @Override
    public void deleteById(Integer idObject) {

        boolean idEncontrado = false;
        if (String.valueOf(idObject).charAt(0) == '2') {
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
                listAll("LIBRO");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else if (String.valueOf(idObject).charAt(0) == '1') {
            try {
                rs.beforeFirst();
                while (rs.next() && !idEncontrado) {
                    user.setIdUser(idObject);
                    if (rs.getInt("idUser") == user.getIdUser()) {
                        idEncontrado = true;
                        rs.deleteRow();
                        System.out.println("User con id " + idObject + " ha sido eliminado");
                    }
                }
                listAll("USER");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("ID no encontrado");
        }
        closeConnection();
    }


    private void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
