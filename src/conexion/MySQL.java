/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;

/**
 *
 * @author Msi
 */
public class MySQL implements Conexion{
    
    private static MySQL instance;
    private static Connection myConn;
    
    private MySQL(){
        //Este constructor crea la conexion
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3340/mydb", "root", "1962");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
    public static MySQL getInstance(){
        
        if(instance == null){
            return new MySQL();
            
        }
        return instance;
        
    }
    
    
    @Override
    public ArrayList<Cliente> getClientes(){
        
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.createStatement();
            
            String query = "SELECT * FROM mydb.Cliente;";
            
            myRs = myStmt.executeQuery(query);
            ArrayList<Cliente> clientes = new ArrayList<>();
            while (myRs.next()) {
                
                int id = myRs.getInt("idCliente");
                String nombre = myRs.getString("nombre");
                String apellido = myRs.getString("apellido");
                int edad = myRs.getInt("edad");
                int idGenero = myRs.getInt("idGenero");
                int idProvincia = myRs.getInt("idProvincia");
                int idCanton = myRs.getInt("idCanton");
                
                String genero = getValuebyId("Genero", "descripcion", idGenero);
                String provincia = getValuebyId("Provincia", "descripcion", idProvincia);
                String canton = getValuebyId("Canton", "descripcion", idCanton);
                
                Cliente cliente = new Cliente(id, nombre, apellido, edad, genero, provincia, canton);
                clientes.add(cliente);
            }
            
            myRs.close();
            myStmt.close();
            return clientes;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Cliente> getClientesbyGenero(String generoObj){
        
        Statement myStmt = null;
        ResultSet myRs = null;
        try {
            myStmt = myConn.createStatement();
            String quryBase = "SELECT * FROM mydb.Cliente as cl\n" +
                                "inner join mydb.Genero as gn on gn.idGenero = cl.idGenero\n" +
                                "where gn.descripcion = \"%s\";";
            String query = String.format(quryBase, generoObj);
            
            myRs = myStmt.executeQuery(query);
            ArrayList<Cliente> clientes = new ArrayList<>();
            while (myRs.next()) {
                
                int id = myRs.getInt("idCliente");
                String nombre = myRs.getString("nombre");
                String apellido = myRs.getString("apellido");
                int edad = myRs.getInt("edad");
                int idGenero = myRs.getInt("idGenero");
                int idProvincia = myRs.getInt("idProvincia");
                int idCanton = myRs.getInt("idCanton");
                
                String genero = getValuebyId("Genero", "descripcion", idGenero);
                String provincia = getValuebyId("Provincia", "descripcion", idProvincia);
                String canton = getValuebyId("Canton", "descripcion", idCanton);
                
                Cliente cliente = new Cliente(id, nombre, apellido, edad, genero, provincia, canton);
                clientes.add(cliente);
            }
            
            myRs.close();
            myStmt.close();
            return clientes;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    @Override
    public String getValuebyId(String tabla, String columna, int id){
        
        try {
            
            Statement myStmt = myConn.createStatement();
            
            String queryBase = "SELECT * FROM mydb.%s where id%s = %d;";
            String query = String.format(queryBase, tabla, tabla, id);
            
            ResultSet myRs = myStmt.executeQuery(query);
            if(myRs.next()){
                
                String value = myRs.getString(columna);
                myRs.close();
                myStmt.close();
                return value;
            }
             
        } catch (SQLException ex) {
            
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;      
    }

    

    
}
