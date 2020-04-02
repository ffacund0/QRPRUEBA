package com.example.qrprueba.entidades;

public class Repuesto {
    private int id;
    private Long codigo;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private String proveedor;
    private String fechaRegistro;
    private String idSuc1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getIdSuc1() {
        return idSuc1;
    }

    public void setIdSuc1(String idSuc1) {
        this.idSuc1 = idSuc1;
    }

    public Repuesto(){

    }
    public Repuesto(int id,Long codigo, String nombre, String descripcion, Integer cantidad, String proveedor,String fechaRegistro,String idSuc1) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.fechaRegistro=fechaRegistro;
        this.idSuc1=idSuc1;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
