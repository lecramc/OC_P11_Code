package com.example.Hospital.Auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";  // Nom de l'en-tête contenant le jeton d'authentification
    private static final String AUTH_TOKEN = "djHB9tuE*CXKnQm33eJA^kLL#db!TU8Dqwf&RueeS7etDNBBgmmTKiRqYJ3CSybggHd4E&b67Vx9";  // Clé API valide

    // Méthode pour obtenir l'objet Authentication à partir de la requête HTTP
    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);  // Récupère la clé API depuis l'en-tête de la requête
        if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {  // Vérifie si la clé API est valide
            throw new BadCredentialsException("Invalid API Key");  // Lance une exception en cas de clé invalide
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);  // Retourne un objet Authentication sans autorité
    }
}
