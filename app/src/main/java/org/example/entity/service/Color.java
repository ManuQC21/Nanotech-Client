package org.example.entity.service;



public class Color {


    private int idcolor;

    private Color.ColoresEnum colores;
    public enum ColoresEnum {
        Negro,
        Blanco,
        Plomo
    }

    private int disponibilidad;

    private Producto producto;
    public int getIdcolor() {
        return idcolor;
    }

    public void setIdcolor(int idcolor) {
        this.idcolor = idcolor;
    }

    public ColoresEnum getColores() {
        return colores;
    }

    public void setColores(ColoresEnum colores) {
        this.colores = colores;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
