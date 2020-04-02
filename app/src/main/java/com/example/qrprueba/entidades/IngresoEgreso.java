package com.example.qrprueba.entidades;

public class IngresoEgreso {
    private int idRep;
    private String idUsr;
    private String idSuc;
    private String accion;
    private String fecha;

    public IngresoEgreso() {
    }

    public IngresoEgreso(int idRep, String idUsr, String idSuc, String accion, String fecha) {
        this.idRep = idRep;
        this.idSuc = idSuc;
        this.idUsr = idUsr;
        this.accion = accion;
        this.fecha = fecha;
    }

    public int getIdRep() {
        return idRep;
    }

    public void setIdRep(int idRep) {
        this.idRep = idRep;
    }

    public String getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(String idUsr) {
        this.idUsr = idUsr;
    }

    public String getIdSuc() {
        return idSuc;
    }

    public void setIdSuc(String idSuc) {
        this.idSuc = idSuc;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
