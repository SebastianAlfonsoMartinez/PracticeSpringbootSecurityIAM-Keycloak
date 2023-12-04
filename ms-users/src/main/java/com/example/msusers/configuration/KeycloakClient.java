package com.example.msusers.configuration;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClient {

    @Value("${dh.keycloak.serverUrl}")
    private String serverUrl;
    @Value("${dh.keycloak.clientId}")
    private String clientId;
    @Value("${dh.keycloak.clientSecret}")
    private String clientSecret;
    @Value("${dh.keycloak.realm}")
    private String realm;

    @Bean
    public Keycloak getInstance(){
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();

    }
}
