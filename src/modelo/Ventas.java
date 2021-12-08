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
public class Ventas {
    int idventa;
    String fecha_venta; 
    int clave_producto;
    String Nombre_Producto;
    double precio;
    int cantidad_vendida;
    String Precio_total;
    double Recibido;
    double Cambio;

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getClave_producto() {
        return clave_producto;
    }

    public void setClave_producto(int clave_producto) {
        this.clave_producto = clave_producto;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad_vendida() {
        return cantidad_vendida;
    }

    public void setCantidad_vendida(int cantidad_vendida) {
        this.cantidad_vendida = cantidad_vendida;
    }

    public String getPrecio_total() {
        return Precio_total;
    }

    public void setPrecio_total(String Precio_total) {
        this.Precio_total = Precio_total;
    }

    

    public double getRecibido() {
        return Recibido;
    }

    public void setRecibido(double Recibido) {
        this.Recibido = Recibido;
    }

    public double getCambio() {
        return Cambio;
    }

    public void setCambio(double Cambio) {
        this.Cambio = Cambio;
    }
     

 
    
    
    
    
}
