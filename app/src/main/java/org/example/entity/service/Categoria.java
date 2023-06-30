package org.example.entity.service;


public class Categoria {

    private int id;

    private String Nombre;

    private boolean Stock;

    private DocumentoAlmacenado Foto;

    public Categoria(int id, String nombre, boolean stock, DocumentoAlmacenado foto) {
        this.id = id;
        Nombre = nombre;
        Stock = stock;
        Foto = foto;
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
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public boolean isStock() {
        return Stock;
    }

    public void setStock(boolean stock) {
        Stock = stock;
    }

    public DocumentoAlmacenado getFoto() {
        return Foto;
    }

    public void setFoto(DocumentoAlmacenado foto) {
        Foto = foto;
    }
}
