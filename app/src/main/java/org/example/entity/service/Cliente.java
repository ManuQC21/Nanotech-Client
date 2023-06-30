package org.example.entity.service;


public class Cliente {

    private int idcliente;

    private String Nombres;

    private String ApellidoPaterno;

    private String ApellidoMaterno;

    private String TipoDoc;

    private String NumDoc;

    private String DireccionEnvio;

    private String Departamento;

    private String Provincia;

    private String Distrito;

    private String Telefono;

    private DocumentoAlmacenado Foto;

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        ApellidoMaterno = apellidoMaterno;
    }

    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        TipoDoc = tipoDoc;
    }

    public String getNumDoc() {
        return NumDoc;
    }

    public void setNumDoc(String numDoc) {
        NumDoc = numDoc;
    }

    public String getDireccionEnvio() {
        return DireccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        DireccionEnvio = direccionEnvio;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public DocumentoAlmacenado getFoto() {
        return Foto;
    }

    public void setFoto(DocumentoAlmacenado foto) {
        Foto = foto;
    }

    public String getNombreCompletoCiente(){
        return this.Nombres != null && this.ApellidoPaterno != null && this.ApellidoMaterno != null ?
                this.Nombres + " " + this.ApellidoPaterno + " " + this.ApellidoMaterno: "-----";
    }

}