package com.example.alertas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlertaRequest {

    @NotBlank(message = "El nombre del paciente es obligatorio")
    private String nombrePaciente;

    @NotBlank(message = "La habitación es obligatoria")
    private String habitacion;

    @NotBlank(message = "El color de alerta es obligatorio")
    private String colorAlerta;

    private String signosVitales;
}
