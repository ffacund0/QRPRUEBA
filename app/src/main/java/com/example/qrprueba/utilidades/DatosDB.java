package com.example.qrprueba.utilidades;

public class DatosDB {
    public static final String CREATE_TABLA_ARTICULO= "CREATE TABLE ARTICULO (codigo BIGINT PRIMARY KEY,nombre VARCHAR(45),descripcion VARCHAR(100),cantidad INT,proveedor VARCHAR(45),fecha_registro DATETIME)";

    public static final int VERSION=1;
    public static final String TABLA_ARTICULO = "ARTICULO";
    public static final String CODIGO="codigo";
    public static final String NOMBRE="nombre";
    public static final String DESCRIPCION="descripcion";
    public static final String CANTIDAD="cantidad";
    public static final String PROVEEDOR="proveedor";
    public static final String FECHA="fecha_registro";
}
