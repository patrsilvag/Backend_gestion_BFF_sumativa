package com.example.alertas.dto; // O el paquete donde tengas tus DTOs en el BFF

public class EstadoRequest {
    private String estado;

    // Getters, Setters y constructor vacío
    public EstadoRequest() {}

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
