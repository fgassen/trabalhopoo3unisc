/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ghelfer
 */
public class DAO {
    
    Connection con;
    
    public DAO() {
           con = new Conexao().getConnection();
    }
    
     public List<String> getListAnwser(String question) {
        List<String> lista = new ArrayList<>();
        try {
            String query = "SELECT * FROM receitas WHERE descricao LIKE '%"+question+"%'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int counter = 0;
            while (rs.next()) {
                String s = "";
                s += "Orçada: "+String.valueOf(rs.getFloat("orcada"));
                s += "Atualizada: "+String.valueOf(rs.getFloat("atualizada"));
                s += "Arrecada mes: "+String.valueOf(rs.getFloat("arrecada_mes"));
                s += "Arrecada Exercicio: "+String.valueOf(rs.getFloat("arrecada_exercicio"));
                System.out.println(s);
                lista.add(s);
                counter +=1;
            }
            rs.close();
            stmt.close();
            con.close();
            
            if(counter > 0){
                return lista;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
  
}
