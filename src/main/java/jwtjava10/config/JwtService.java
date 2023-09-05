package jwtjava10.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jwtjava10.models.Role;
import jwtjava10.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author Mukhammed Asantegin
 */
@Service
public class JwtService {

    @Value("${app.jwt.secret}")
    private  String jwtSecret ;
    //create jwt

    public String createToken(User user){
        Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
       return JWT.create()
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withClaim("id", user.getId())
                .withClaim("fullName", user.getFullName())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole().getAuthority())
                .sign(algorithm);
    }

    // verify jwt
    public User verifyToken(String token){
        Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        return decodedJWTConverToUser(decodedJWT);
    }

    private User decodedJWTConverToUser(DecodedJWT decodedJWT) {
        User user = new User();
        user.setId(decodedJWT.getClaim("id").asLong());
        user.setFullName(decodedJWT.getClaim("fullName").asString());
        user.setEmail(decodedJWT.getClaim("email").asString());
        user.setRole(Role.valueOf(decodedJWT.getClaim("role").asString()));
        return user;
    }


}
