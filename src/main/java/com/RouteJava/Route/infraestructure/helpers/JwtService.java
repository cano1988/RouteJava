package com.RouteJava.Route.infraestructure.helpers;


import com.RouteJava.Route.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    //1. Crear la firma o clave
    // https://www.base64encode.org/es/ se extrae de base 64
    private final String SECRET_KEY = "QW5kcmVzIGVzIGVsIHN1cGVyIGNhbXBlb24gZGUgdG9kb3MgbG9zIHRpZW1wb3MgeSBsZSBndXN0YSBoYWNlciBlamVjZXJjaWNpbyB5IGp1Z2FyIGZ1dGJsbyBsb3MgZmluZXMgZGUgc2VtYW5h";

    //2. MÉTODO PARA ENCRIPTAR LA CLAVE SECRETA
    public SecretKey getKey(){
        //Convertir la llave a bytes
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        //Retornamos la llave cifrada
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //3. CONSTRUIR EL JWT
    public String getToken(Map<String,Object> claims, User user){

        return Jwts.builder()
                .claims(claims) //Agrego el cuerpo del jwt
                .subject(user.getUsername()) //Agrego de quien es el jwt
                .issuedAt(new Date(System.currentTimeMillis())) //Fecha de creación
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) //Fecha de expiración
                .signWith(this.getKey()) //firmar el token
                .compact();
    }
    //4. Método para obtener el jwt
    public String getToken(User user){
        //Crear el map de claims
        Map<String,Object> claims = new HashMap<>();

        claims.put("id", user.getId());
        claims.put("role", user.getRole().name());

        return getToken(claims, user);
    }

    // Desestructurar el jwt para obtener todos los claims
    public Claims getAllClaims(String token){
        return Jwts
                .parser() //Desarmar el jwt
                .verifyWith(this.getKey()) //Validamos que sea la misma firma con la se creo
                .build() //Lo construimos de nuevo
                .parseSignedClaims(token) // convertimos el token a base 10 - base64
                .getPayload(); //Extraemos el payload (claims)
    }

    //Método para obtener un claim en especifico
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){

        //Obtener los claims
        final Claims claims = this.getAllClaims(token);

        return claimsResolver.apply(claims);

    }

    //Método para obtener el subject del jwt
    public String getUsernameFromToken(String token){
        return this.getClaim(token, Claims::getSubject );
    }

    //Obtener la fecha de expiración del token
    public Date getExpiration(String token){
        return this.getClaim(token, Claims::getExpiration);
    }

    //Método para validar si el token está expirado
    public boolean isTokenExpired(String token){
        return this.getExpiration(token).before(new Date());
    }

    //Método para validar si el token es válido
    public boolean isTokeValid(String token, UserDetails user){

        String userName= this.getUsernameFromToken(token);
        //Si el usuario que viene en el token coincide con alguno de la db y ademas el token no está expirado entonces es  válido
        return userName.equals(user.getUsername()) && !this.isTokenExpired(token);
    }
}
