package com.example.alertas.controllers;

import com.example.alertas.dto.AlertaRequest;
import com.example.alertas.dto.AlertaResponse;
import com.example.alertas.dto.EstadoRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final RestTemplate restTemplate;

    @Value("${ms.alertas.url}")
    private String msAlertasUrl;

    public AlertaController(RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<AlertaResponse>> listar() {
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
            @RequestBody EstadoRequest request) {
        // Al usar el RestTemplate de Spring Boot, no necesitas configuraciones extras
        return restTemplate.exchange(msAlertasUrl + "/api/alertas/" + id + "/estado",
                HttpMethod.PATCH, new HttpEntity<>(request), AlertaResponse.class);
    }

    // BFF - AlertaController.java
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            String url = msAlertasUrl + "/api/alertas/" + id;
            System.out.println("BFF intentando borrar en: " + url);

            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("ERROR EN BFF AL LLAMAR AL MS: " + e.getMessage());
            throw e; // Esto disparará el 500 que ves en el log
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<AlertaResponse> actualizar(@PathVariable Long id,
            @RequestBody AlertaRequest request) {
        // Definimos la URL del microservicio
        String url = msAlertasUrl + "/api/alertas/" + id;

        // Usamos exchange para enviar el método PUT con el cuerpo (request)
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(request), // El cuerpo de
                                                                                     // la petición
                AlertaResponse.class);
    }

}
