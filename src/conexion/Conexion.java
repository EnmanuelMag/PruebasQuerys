/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.util.ArrayList;
import modelo.Cliente;

/**
 *
 * @author Msi
 */
public interface Conexion {
      
    
    public ArrayList<Cliente> getClientes();
    
    public String getValuebyId(String tabla, String columna, int id);
    
    public ArrayList<Cliente> getClientesbyGenero(String generoObj);
}
