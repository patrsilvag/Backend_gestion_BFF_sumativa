package com.example.alertas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlertaResponse {

    private Long id;
    private String nombrePaciente;
    private String habitacion;
    private String colorAlerta;
    private String signosVitales;
    private LocalDateTime fechaHora;
}
