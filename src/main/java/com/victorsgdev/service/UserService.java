package com.victorsgdev.service;

import com.victorsgdev.domain.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
public class UserService implements Service{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Object> listAll(String tabla) {
        return Collections.singletonList(userRepository.listAll(tabla));
    }

    @Override
    public Object save(Object object) {
        return userRepository.save((User) object);
    }

    @Override
    public Object updateById(Integer idObject, Object object) {
        return userRepository.updateById(idObject, (User) object);
    }

    @Override
    public void deleteById(Integer idObject) {
        userRepository.deleteById(idObject);
    }

    @Override
    public Object listById(Integer idObject) {
        return userRepository.listById(idObject);
    }

    // CSV:
    public void exportToCsv(Writer writer, String tabla) {

        List<User> users = userRepository.listAll(tabla);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (User user : users) {
                csvPrinter.printRecord(user.getIdUser(),user.getName(),user.getNif());
            }
        } catch (IOException e) {
            System.out.println("Error al exportar al csv: "+e);
        }
    }
}
