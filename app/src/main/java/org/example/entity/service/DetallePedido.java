package org.example.entity.service;


public class DetallePedido {
    private int id;
    private int cantidad;
    private Double precio;
    private Producto producto;
    private Pedido pedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Producto getProducto(){
            return producto;
    }
    public void setProducto(Producto producto){
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void addOne() {
        this.cantidad++;
    }

    public void removeOne() {
        this.cantidad--;
    }

    public double getTotal() {
        return this.cantidad * this.precio;
    }
}