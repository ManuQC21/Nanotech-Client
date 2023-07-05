package org.example.entity.service;


public class tarjeta {


    private int id;
    private String numero;
    private String cvv;
    private String fechaVencimiento;

    public tarjeta() {
    }

    public tarjeta(int id, String numero, String cvv, String fecha) {
        this.id = id;
        this.numero = numero;
        this.cvv = cvv;
        this.fechaVencimiento = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
