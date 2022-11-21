package com.cieep.ejercicio02.modelos;

import java.io.Serializable;

public class PagoHora implements Serializable {

    private String matricula;
    private float tiempo;
    private float importe;

    public PagoHora() {
    }

    public PagoHora(String matricula, float tiempo, float importe) {
        this.matricula = matricula;
        this.tiempo = tiempo;
        this.importe = importe;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getTiempo() {
        return tiempo;
    }

    public void setTiempo(float tiempo) {
        this.tiempo = tiempo;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "PagoHora{" +
                "matricula='" + matricula + '\'' +
                ", tiempo=" + tiempo +
                ", importe=" + importe +
                '}';
    }
}
