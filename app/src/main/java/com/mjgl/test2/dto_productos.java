package com.mjgl.test2;

public class dto_productos {

    int codigo;
    String descripcion;
    double precio;
    int idcategoria;

    public dto_productos() {
    }

    public dto_productos(int codigo, String descripcion, double precio, int idcategoria) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.idcategoria = idcategoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }
}
