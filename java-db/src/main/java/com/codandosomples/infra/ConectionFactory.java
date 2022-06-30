package com.codandosomples.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
@author Matheus Macedo Longo
 */
public class ConectionFactory {

    private ConectionFactory(){}
    public static Connection getConnection(){
        try {
         return  DriverManager.getConnection("jdbc:postgresql://localhost:5432/Java_exercicio", "postgres","postgres");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
