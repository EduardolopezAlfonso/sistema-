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
public class ComprasDAO {
    conexion con = new conexion();
    PreparedStatement psql;
    ResultSet rs;
    
    public int GuardarCompras(int clave_producto,String fecha_compra,int cantidad_comprada, int id_proveedor) {
        int resultado = 0;
        Connection cn = null;

        String SentenciaSQL = "INSERT INTO compras (clave_producto,fecha_compra,cantidad_comprada,id_proveedor)"
                + " VALUES (?,?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            //psql.setInt(1,0);
            psql.setInt(1, clave_producto);
            psql.setString(2, fecha_compra);
            psql.setInt(3, cantidad_comprada);
            psql.setInt(4, id_proveedor);
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
    
    public ArrayList<Compras> listCompras(){
        ArrayList listaCompras= new ArrayList();
        Compras compras;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM compras";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                compras =new Compras();
                compras.setClave_producto(rs.getInt(1));
                compras.setFecha_compra(rs.getString(2));
                compras.setCantidad_comprada(rs.getInt(3));
                compras.setId_proveedor(rs.getInt(4));
                
                listaCompras.add(compras);                
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
        return listaCompras;        
    }
    public void editarCompras(int clave_producto,String fecha_compra,int cantidad_comprada, int id_proveedor){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "UPDATE compras SET fecha_compra=?,cantidad_comprada=? WHERE clave_producto=? and id_proveedor=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setString(1, fecha_compra);
            psql.setInt(2, cantidad_comprada);
            psql.setInt(3, clave_producto);
            psql.setInt(4, id_proveedor);
            
            
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
     public int eliminarCompra(int clave){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM compras WHERE clave_producto=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1,clave);
            
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
