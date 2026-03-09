package br.com.aal.document_service.controller;

import br.com.aal.document_service.service.AuthorizationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("documents")
public class DocumentController {

    private final Logger LOOGER = LogManager.getLogger(DocumentController.class);

    private final AuthorizationService authorizationService;

    public DocumentController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public List<String> listDocuments(@AuthenticationPrincipal Jwt jwt) throws AccessDeniedException {
        LOOGER.info("listDocuments");

        if (!authorizationService.isAllowed(jwt, "read")) {
            throw new AccessDeniedException("Forbidden");
        }

        return List.of("contract.pdf", "report.pdf");
    }

    @PostMapping
    public String createDocument() {
        return "document created!";
    }
}
