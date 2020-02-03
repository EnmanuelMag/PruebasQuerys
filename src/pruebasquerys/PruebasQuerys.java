/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebasquerys;

import conexion.Conexion;
import conexion.MySQL;
import java.sql.Connection;
import java.util.ArrayList;
import modelo.Cliente;

/**
 *
 * @author Msi
 */
public class PruebasQuerys {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Paso 1");
        Conexion con = MySQL.getInstance();
        System.out.println("Fin paso 1");
        System.out.println("Paso 2");
        ArrayList<Cliente> clientes = con.getClientes();
        System.out.println("Fin paso 2");
        clientes.forEach((c) -> {
            System.out.println(c);
        });
        
        System.out.println("Paso 2-2");
        ArrayList<Cliente> clientes2 = con.getClientesbyGenero("masculino");
        System.out.println("Fin paso 2-2");
        clientes2.forEach((c) -> {
            System.out.println(c);
        });
        
        
    }
    
}
