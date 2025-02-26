package com.example.prj.security;

import com.example.prj.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Autowired
    private JwtConfig jwtConfig;

    public String generateToken(String username) {
        // Convertir la chaîne secrète en un objet Key
        byte[] keyBytes = jwtConfig.getSecret().getBytes();
        Key key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

        // Générer le token avec la clé
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(key)  // Utilisation de la clé plutôt que la chaîne secrète
                .compact();
    }
}
