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
public class ProveedoresDAO {
    conexion con = new conexion();
    PreparedStatement psql;
    ResultSet rs;

    public int GuardarProveedores(int id_proveedor, String nombre_razon_social, String ciudad, int telefono, String representante) {
        int resultado = 0;
        Connection cn = null;
        

        String SentenciaSQL = "INSERT INTO proveedores (id_proveedor,nombre_razon_social,ciudad,telefono,representante)"
                + " VALUES (?,?,?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            //psql.setInt(1,0);
            psql.setInt(1, id_proveedor);
            psql.setString(2, nombre_razon_social);
            psql.setString(3, ciudad);
            psql.setInt(4, telefono);
            psql.setString(5, representante);
            
            
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
     public ArrayList<Proveedores> listProveedores(){
        ArrayList listaProveedores= new ArrayList();
        Proveedores proveedores = null;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM proveedores";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                proveedores= new Proveedores();
                proveedores.setId_proveedor(rs.getInt(1));
                proveedores.setNombre_razon_social(rs.getString(2));
                proveedores.setCiudad(rs.getString(3));
                proveedores.setTelefono(rs.getInt(4));
                proveedores.setRepresentante(rs.getString(5));
                
                listaProveedores.add(proveedores);                
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
        return listaProveedores;
     }
     public void editarProovedor(int id_proveedor, String nombre_razon_social, String ciudad, int telefono, String representante){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "UPDATE proveedores SET id_proveedor=?,nombre_razon_social=?,ciudad=?,telefono=?,representante=? WHERE id_proveedor=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1, id_proveedor);
            psql.setString(2, nombre_razon_social);
            psql.setString(3, ciudad);
            psql.setInt(4, telefono);
            psql.setString(5, representante);
            psql.setInt(6, id_proveedor);
            
            resultado = psql.executeUpdate();
            System.out.println("resultdo edit="+resultado);
            if (resultado>0){ //si resultado es >0 se ejecuto la transaccion correctamente
                JOptionPane.showMessageDialog(null, "El registro se actualizó correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro");
            }
            psql.close();
        } catch (SQLException e){
            System.err.println(e);
        } 
        //return resultado;
    }
     public int eliminarProovedor(int id){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM proveedores WHERE id_Proveedor=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1,id);
            
            resultado = psql.executeUpdate();
            if (resultado>0){ //si resultado es >0 se ejecuto la transaccion correctamente
                JOptionPane.showMessageDialog(null, "El registro se eliminó correctamente");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
            }
            psql.close();
        } catch (SQLException e){
            System.err.println(e);
        } 
        return resultado;
    }
    
}
