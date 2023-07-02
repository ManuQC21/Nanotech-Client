package org.example.entity.service;


public class Categoria {

    private int id;

    private String nombre;

    private boolean stock;

    private DocumentoAlmacenado foto;

    public Categoria(int id, String nombre, boolean stock, DocumentoAlmacenado foto) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.foto = foto;
    }

    public Categoria() {

    }

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

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public DocumentoAlmacenado getFoto() {
        return foto;
    }

    public void setFoto(DocumentoAlmacenado foto) {
        this.foto = foto;
    }
}
