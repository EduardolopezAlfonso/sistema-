/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JENNY
 */
public class ClientesDAO {
    conexion con = new conexion();
    PreparedStatement psql;
    ResultSet rs;

    public int GuardarClientes(int id_cliente, String nombre, String direccion) {  //atributos
        int resultado = 0;
        Connection cn = null;

        String SentenciaSQL = "INSERT INTO clientes (id_cliente,nombre,direccion)"
                + " VALUES (?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            //psql.setInt(1,0);
            psql.setInt(1, id_cliente);
            psql.setString(2, nombre);
            psql.setString(3, direccion);
            resultado = psql.executeUpdate();
            if (resultado>0){ //si resultado es >0 se ejecuto la transaccion correctamente
                JOptionPane.showMessageDialog(null, "Se guardo correctamente el registro");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo guardar el registro");
            }
            psql.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al intentar almacenar guardar un registro:\n"
                    + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            System.out.println(e);
        } finally {
            try {
                if (cn != null) {
                    con.cerrar();
                }
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resultado;
    }
     public ArrayList<Clientes> listClientes(){
        ArrayList listaClientes= new ArrayList();
        Clientes clientes = null;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM clientes";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                clientes= new Clientes();
                clientes.setId_cliente(rs.getInt(1));
                clientes.setNombre(rs.getString(2));
                clientes.setDireccion(rs.getString(3));
                
                listaClientes.add(clientes);                
            }            
        }catch(SQLException e){
            System.err.println(e);            
        }
        finally {
            try {
                if (cn != null) {
                    con.cerrar();
                }
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión:\n"
                        + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listaClientes;
     }
}
    
  
