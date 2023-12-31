package org.example.entity.service;


import java.util.List;

public class Producto {


    private int id;
    private String nombre;
    private DocumentoAlmacenado foto;
    private Double  precio;
    private int stock;
    private String descripcion;
    private Categoria categoria;
    private boolean vigencia;
    private boolean recomendado;
    private String medida;
    private String documento;
    private String razonsocial;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getRazonsocial() {
        return razonsocial;
    }
    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getMedida() {
        return medida;
    }
    public void setMedida(String medida) {
        this.medida = medida;
    }
    public boolean isRecomendado() {
        return recomendado;
    }
    public void setRecomendado(boolean recomendado) {
        this.recomendado = recomendado;
    }
    public DocumentoAlmacenado getFoto() {
        return foto;
    }
    public void setFoto(DocumentoAlmacenado foto) {
        this.foto = foto;
    }
    public boolean isVigencia() {
        return vigencia;
    }
    public void setVigencia(boolean vigencia) {
        this.vigencia = vigencia;
    }
}
