package br.com.aal.document_service.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthorizationService {

    private final Logger LOGGER = LogManager.getLogger(AuthorizationService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean isAllowed(Jwt jwt, String action) {

        Map<String, Object> payload = Map.of(
                "input", Map.of(
                        "jwt", jwt.getClaims(),
                        "action", action
                )
        );

        Map response =
                restTemplate.postForObject(
                        "http://localhost:8181/v1/data/document/allow",
                        payload,
                        Map.class
                );

        Boolean allowed = (Boolean) response.get("result");

        LOGGER.info("OPA decision -> action={} allowed={}",
                action,allowed);

        return Boolean.TRUE.equals(allowed);
    }
}
