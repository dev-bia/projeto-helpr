package org.soulcodeacademy.helpr.security;

//objetivo dessa classe é validar JWT e extrair os dados do JWT

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // instanciar automaticamente o TokenUtil
public class TokenUtil {

    //public static void main(String[]args){
    //  String senha = "12345678";
    //  String token = JWT.create()
    //          .withSubject("jr@gmail.com") // de quem pertence o token
    //         .withClaim("nome", "batata") // informação adicional
    //         .sign(Algorithm.HMAC512(senha));
    //  String email = JWT.require(Algorithm.HMAC512(senha)).build().verify(token).getSubject();
    //  System.out.println(email);
    //}

    @Value("${senhaJwt}") // injeta o valor da variável no campo abaixo
    private String senhaJwt;

    @Value("${validadeJwt}")
    private Long validadeJWT;

    public String gerarToken(String email, String perfil){
        // System.currentTimeMillis() => Pega o momento atual em ms
        // new Date(System.currentTimeMillis() + this.validadeJwt) => Indica a data futura que o token vai expirar

        return JWT.create()
                .withSubject(email)
                .withClaim("perfil", perfil)
                .withExpiresAt(new Date(System.currentTimeMillis() + this.validadeJWT))
                .sign(Algorithm.HMAC512(this.senhaJwt));
    }

    public String extrairEmail(String token){
        return JWT.require(Algorithm.HMAC512(this.senhaJwt))
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validarToken(String token){
        // Caso ocorra erro na linha 42, o token passado é inválido:
        // Não foi gerado por nós ou expirou
       try{
           JWT.require(Algorithm.HMAC512(this.senhaJwt))
                   .build()
                   .verify(token);
       return true;
       } catch(JWTVerificationException ex){
           return false;
       }
    }
}
