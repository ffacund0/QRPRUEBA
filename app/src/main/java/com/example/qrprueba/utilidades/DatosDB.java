package com.example.qrprueba.utilidades;

public class DatosDB {
    public static final int VERSION=1;

//**************TABLA SUCURSAL***********//
    public static final String TABLA_SUCURSAL = "SUCURSAL";
    public static final String SUC_ID = "id";
    public static final String SUC_DIRE = "direccion";
    public static final String CREATE_TABLA_SUCURSAL="CREATE TABLE " +TABLA_SUCURSAL+ " ("
            +SUC_ID+" VARCHAR(45) PRIMARY KEY,"
            +SUC_DIRE+" VARCHAR(45))";


    //**************TABLA REPUESTO***********//
    public static final String TABLA_REPUESTO = "REPUESTO";
    public static final String REP_ID = "id";
    public static final String REP_CODIGO="codigo";
    public static final String REP_NOMBRE="nombre";
    public static final String REP_DESCRIPCION="descripcion";
    public static final String REP_CANTIDAD="cantidad";
    public static final String REP_PROVEEDOR="proveedor";
    public static final String REP_FECHA="fecha_registro";
    public static final String REP_SUC_ID="idSuc1";

    public static final String CREATE_TABLA_REPUESTO="CREATE TABLE "+TABLA_REPUESTO+" ("
            +REP_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +REP_CODIGO+" BIGINT,"
            +REP_NOMBRE+" VARCHAR(45),"
            +REP_DESCRIPCION+" VARCHAR(100),"
            +REP_CANTIDAD+" INTEGER,"
            +REP_PROVEEDOR+" VARCHAR(45),"
            +REP_FECHA+" DATETIME,"
            +REP_SUC_ID+" VARCHAR(45))";


    //**************TABLA USUARIO***********/
    public static final String TABLA_USUARIO = "USUARIO";
    public static final String USU_ID= "id";
    public static final String USU_CARGO = "cargo";
    public static final String CREATE_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+" ("
            +USU_ID+" VARCHAR(45) PRIMARY KEY,"
            +USU_CARGO+" VARCHAR(45))";


    //**************TABLA IEREPUESTO***********//
    public static final String TABLA_INGRESOEGRESO = "INGRESOEGRESO";
    public static final String IER_REP_ID = "idRep";
    public static final String IER_USU_ID = "idUsr";
    public static final String IER_SUC_ID = "idSuc";
    public static final String IER_ACCION = "accion";//ingreso o egreso
    public static final String IER_FECHA ="fecha";//la fecha en la que se modifico el registro
    public static final String CREATE_TABLA_INGRESOEGRESO="CREATE TABLE "+TABLA_INGRESOEGRESO +" ("
            +IER_REP_ID+" INTEGER,"
            +IER_SUC_ID+" VARCHAR(45), "
            +IER_USU_ID +" VARCHAR(45), "
            +IER_ACCION+" VARCHAR(45),"
            +IER_FECHA+" DATETIME)";
}
