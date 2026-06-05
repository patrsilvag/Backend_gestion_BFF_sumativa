package com.example.alertas.services;

import com.example.alertas.dto.AlertaRequest;
import com.example.alertas.dto.AlertaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import java.util.Arrays;
import java.util.List;

@Service
public class AlertaService {

    private final RestTemplate restTemplate;

    @Value("${ms.alertas.url}")
    private String msAlertasUrl;

    public AlertaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<AlertaResponse> listarTodas() {
        AlertaResponse[] alertas =
                restTemplate.getForObject(msAlertasUrl + "/api/alertas", AlertaResponse[].class);
        return alertas != null ? Arrays.asList(alertas) : List.of();
    }

    public AlertaResponse buscarPorId(Long id) {
        return restTemplate.getForObject(msAlertasUrl + "/api/alertas/" + id, AlertaResponse.class);
    }

    public AlertaResponse guardar(AlertaRequest request) {
        return restTemplate.postForObject(msAlertasUrl + "/api/alertas", request,
                AlertaResponse.class);
    }

    public AlertaResponse actualizar(Long id, AlertaRequest detalles) {
        // Usamos exchange para PUT
        return restTemplate.exchange(msAlertasUrl + "/api/alertas/" + id, HttpMethod.PUT,
                new HttpEntity<>(detalles), AlertaResponse.class).getBody();
    }

    public void eliminar(Long id) {
        restTemplate.delete(msAlertasUrl + "/api/alertas/" + id);
    }

    public AlertaResponse actualizarEstado(Long id, String nuevoEstado) {
        // En tu PATCH, el MS interno recibe el nuevoEstado en el body
        return restTemplate.patchForObject(msAlertasUrl + "/api/alertas/" + id + "/estado",
                nuevoEstado, AlertaResponse.class);
    }
}
