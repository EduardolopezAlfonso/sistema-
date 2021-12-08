/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 *
 * @author JENNY
 */
public class Compras {
    int clave_producto;
    String fecha_compra; 
    int cantidad_comprada;
    int id_proveedor;

    public int getClave_producto() {
        return clave_producto;
    }

    public void setClave_producto(int clave_producto) {
        this.clave_producto = clave_producto;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getCantidad_comprada() {
        return cantidad_comprada;
    }

    public void setCantidad_comprada(int cantidad_comprada) {
        this.cantidad_comprada = cantidad_comprada;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }
    
    
    
    
    
    
    
}
