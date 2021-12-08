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
public class ProductosDAO {
    conexion con = new conexion();
    PreparedStatement psql;
    ResultSet rs;

    //metodos                                               //parametros
    public int GuardarProductos(int clave_producto, String nombre, String presentacion, float precio_compra, float precio_venta,int existencias) {
        int resultado = 0;
        Connection cn = null;

        String SentenciaSQL = "INSERT INTO productos (clave_producto,nombre,presentacion,precio_compra,precio_venta,existencias)"
                + " VALUES (?,?,?,?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            //psql.setInt(1,0);
            psql.setInt(1, clave_producto);
            psql.setString(2, nombre);
            psql.setString(3, presentacion);
            psql.setFloat(4, precio_compra);
            psql.setFloat(5, precio_venta);
            psql.setInt(6, existencias);
            
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
     public ArrayList<Productos> listProductos(){    //listar productos
        ArrayList listaProductos= new ArrayList();
        Productos productos = null;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM productos";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                productos= new Productos();
                productos.setClave_producto(rs.getInt(1));
                productos.setNombre(rs.getString(2));
                productos.setPresentacion(rs.getString(3));
                productos.setPrecio_compra(rs.getFloat(4));
                productos.setPrecio_venta(rs.getFloat(5));
                productos.setExistencias(rs.getInt(6));
                listaProductos.add(productos);                
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
        return listaProductos;
     }
     public void editarProducto(int clave_producto, String nombre, String presentacion, float precio_compra, float precio_venta,int existencias){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "UPDATE productos SET nombre=?,presentacion=?,precio_compra=?,precio_venta=?,existencias=? WHERE clave_producto=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            
            psql.setString(1, nombre);
            psql.setString(2, presentacion);
            psql.setFloat(3, precio_compra);
            psql.setFloat(4, precio_venta);
            psql.setInt(5, existencias);
            psql.setInt(6, clave_producto);
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
     public int eliminarProducto(int clave_producto){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM productos WHERE clave_producto=?";
               
        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            psql.setInt(1,clave_producto);
            
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
