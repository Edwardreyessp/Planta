package com.escom.planta.entidades;

public class Plantas {

    private int id;
    private String nombre;
    private String dias_riego;
    private String tamano;

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

    public String getDias_riego() {
        return dias_riego;
    }

    public void setDias_riego(String dias_riego) {
        this.dias_riego = dias_riego;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
}
