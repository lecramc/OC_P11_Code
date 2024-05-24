package com.example.Hospital.Auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author clementmarcel
 */
public class ApiKeyAuthentication extends AbstractAuthenticationToken {

    private final String apiKey;  // Stocke la clé API

    // Constructeur qui initialise la clé API et les autorités
    public ApiKeyAuthentication(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);  // Appelle le constructeur de la classe parente avec les autorités
        this.apiKey = apiKey;  // Initialise la clé API
        setAuthenticated(true);  // Définit l'authentification comme réussie
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return apiKey;
    }

}
