package com.amauri.algafood.core.security.authorizationserver;

import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.List;

public interface OAuth2AuthorizationQueryService {

    List<RegisteredClient> listClientsWithConsent(String principalName);

    List<OAuth2Authorization> listAuthorizationsWithConsent(String principalName, String clientId);
}
