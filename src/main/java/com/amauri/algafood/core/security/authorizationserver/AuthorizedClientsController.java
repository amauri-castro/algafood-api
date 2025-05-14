package com.amauri.algafood.core.security.authorizationserver;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorizedClientsController {

    private final OAuth2AuthorizationQueryService authorizationQueryService;

    private final RegisteredClientRepository registeredClientRepository;

    private final OAuth2AuthorizationConsentService authorizationConsentService;
    private final OAuth2AuthorizationService oAuth2AuthorizationService;

    @GetMapping("/oauth2/authorized-clients")
    public String listAuthorizedClients(Principal principal, Model model) {
        List<RegisteredClient> clients = authorizationQueryService.listClientsWithConsent(
                principal.getName());

        model.addAttribute("clients", clients);

        return "pages/authorized_clients";
    }

    @PostMapping("/oauth2/authorized-clients/revoke")
    public String revokeAuthorizedClient(Principal principal,
                                         Model model,
                                         @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId) {

        RegisteredClient registeredClient = this.registeredClientRepository.findByClientId(clientId);

        if (registeredClient == null) {
            throw new AccessDeniedException(String.format("Cliente %s não encontrado", clientId));
        }

        OAuth2AuthorizationConsent consent = this.authorizationConsentService.findById(registeredClient.getId(),
                principal.getName());

        List<OAuth2Authorization> authorizations = this.authorizationQueryService.listAuthorizationsWithConsent(
                principal.getName(), registeredClient.getId());

        if (consent != null) {
            this.authorizationConsentService.remove(consent);
        }

        for (OAuth2Authorization authorization : authorizations) {
            this.oAuth2AuthorizationService.remove(authorization);
        }


        return "redirect:/oauth2/authorized-clients";
    }
}
