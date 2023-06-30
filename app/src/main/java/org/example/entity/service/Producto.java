package org.example.entity.service;


public class Producto {


    private int id;

    private String Nombre;

    private MedidaEnum Medida;
    public enum MedidaEnum {
        Unidades,
        Caja
    }
    private String Documento;
    private int Cantidad;
    private String RazonSocial;
    private int Precio;
    private String Descripcion;
    private String Color;
    private Categoria Categoria;
    private boolean Stock;
    private boolean vigencia;
    private boolean recomendado;
    private DocumentoAlmacenado Foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public MedidaEnum getMedida() {
        return Medida;
    }

    public void setMedida(MedidaEnum medida) {
        Medida = medida;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String documento) {
        Documento = documento;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        RazonSocial = razonSocial;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }


    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public Categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.Categoria = categoria;
    }

    public boolean isStock() {
        return Stock;
    }

    public void setStock(boolean stock) {
        Stock = stock;
    }

    public boolean isRecomendado() {
        return recomendado;
    }

    public void setRecomendado(boolean recomendado) {
        this.recomendado = recomendado;
    }

    public DocumentoAlmacenado getFoto() {
        return Foto;
    }

    public void setFoto(DocumentoAlmacenado foto) {
        Foto = foto;
    }

    public boolean isVigencia() {
        return vigencia;
    }

    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }
}
