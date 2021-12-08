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
public class VentasDAO {
    conexion con = new conexion();
    PreparedStatement psql;
    ResultSet rs;
    
    public int GuardarVentas(String fecha_venta, int clave_producto,String Nombre_Producto, double precio, int cantidad_vendida, String Precio_total, double Recibido, double Cambio) {
        int resultado = 0;
        Connection cn = null;

        String SentenciaSQL = "INSERT INTO ventas ( fecha_venta, clave_producto, Nombre_Producto, precio, cantidad_vendida, Precio_total, Recibido,Cambio)"
                + " VALUES (?,?,?,?,?,?,?,?)";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            //psql.setInt(1,0);
            
             psql.setString(1, fecha_venta);
            psql.setInt(2, clave_producto);
            psql.setString(3, Nombre_Producto);
            psql.setDouble(4, precio);
            psql.setInt(5, cantidad_vendida);
            psql.setString(6, Precio_total);
            psql.setDouble(7, Recibido);
            psql.setDouble(8, Cambio);
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
    
    public ArrayList<Ventas> listVenta(){
        ArrayList listaVentas= new ArrayList();
        Ventas ventas;
        
        Connection cn = null;
        String SentenciaSQL = "SELECT * FROM ventas";

        try {
            cn = con.Conectar();
            psql = cn.prepareStatement(SentenciaSQL);
            ResultSet rs=psql.executeQuery();
            while (rs.next()){
                ventas =new Ventas();
                ventas.setIdventa(rs.getInt(1));
                ventas.setFecha_venta(rs.getString(2));
                ventas.setClave_producto(rs.getInt(3));
                ventas.setNombre_Producto(rs.getString(4));
                ventas.setPrecio(rs.getDouble(5));
                ventas.setCantidad_vendida(rs.getInt(6));
                ventas.setPrecio_total(rs.getString(7));
                ventas.setRecibido(rs.getDouble(8));
                ventas.setCambio(rs.getDouble(9));
                
                listaVentas.add(ventas);                
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
        return listaVentas;        
    }
    public void editarVentas( String fecha_venta, int clave_producto,String Nombre_Producto, double precio, int cantidad_vendida, String Precio_total, double Recibido, double Cambio){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "UPDATE ventas SET fecha_venta=?,clave_producto=?,Nombre_Producto=?,precio=?,cantidad_vendida=?,Precio_total=?, Recibido=?, cambio=? WHERE clave_producto=?";
               
        try {
            cn = con.Conectar();
            
             psql.setString(1, fecha_venta);
            psql.setInt(2, clave_producto);
            psql.setString(3, Nombre_Producto);
            psql.setDouble(4, precio);
            psql.setInt(5, cantidad_vendida);
            psql.setString(6, Precio_total);
            psql.setDouble(8, Recibido);
            psql.setDouble(9, Cambio);
            
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
    public int eliminarVenta(int clave_producto){
        int resultado=0;
        Connection cn = null;

        String SentenciaSQL = "DELETE FROM ventas WHERE clave_producto=?";
               
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
