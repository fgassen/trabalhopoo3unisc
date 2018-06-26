/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc;


import java.sql.*;
/**
 *
 * @author ghelfer
 */
public class Conexao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/exerc1";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL,USER,PASS);
        }
        catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
