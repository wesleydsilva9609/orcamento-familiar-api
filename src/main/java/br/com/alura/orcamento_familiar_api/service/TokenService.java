package br.com.alura.orcamento_familiar_api.service;

import br.com.alura.orcamento_familiar_api.entities.Usuario;
import com.auth0.jwt.JWT;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("orcamento-familia-api")
                    .withSubject(usuario.getUsuario())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("impossivel gerar token");
        }
    }

    public String getSubject(String tokenJWT){

        try {
            var algorithm = Algorithm.HMAC256("123456");
            return  JWT.require(algorithm)
                    .withIssuer("orcamento-familia-api")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("tokenJWT invalido");
        }

    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
