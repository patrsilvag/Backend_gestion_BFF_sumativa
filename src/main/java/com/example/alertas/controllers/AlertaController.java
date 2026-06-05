package com.example.alertas.controllers;

import com.example.alertas.dto.AlertaRequest;
import com.example.alertas.dto.AlertaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/alertas")
// El CORS se maneja centralizado en SecurityConfig, se recomienda quitarlo de aquí
public class AlertaController {

    private final RestTemplate restTemplate;

    @Value("${ms.alertas.url}")
    private String msAlertasUrl;

    public AlertaController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<AlertaResponse>> listar() {
        // Redirige al microservicio interno
        AlertaResponse[] alertas =
                restTemplate.getForObject(msAlertasUrl + "/api/alertas", AlertaResponse[].class);
        return ResponseEntity.ok(Arrays.asList(alertas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(restTemplate.getForObject(msAlertasUrl + "/api/alertas/" + id,
                AlertaResponse.class));
    }

    @PostMapping
    public ResponseEntity<AlertaResponse> crear(@RequestBody AlertaRequest request) {
        return ResponseEntity.ok(restTemplate.postForObject(msAlertasUrl + "/api/alertas", request,
                AlertaResponse.class));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<AlertaResponse> cambiarEstado(@PathVariable Long id,
            @RequestBody String nuevoEstado) {
        // El BFF orquesta la actualización llamando al MS interno
        restTemplate.patchForObject(msAlertasUrl + "/api/alertas/" + id + "/estado", nuevoEstado,
                Void.class);
        return ResponseEntity.ok().build();
    }

    // PUT y DELETE se implementan siguiendo la misma lógica de redirección...
}
