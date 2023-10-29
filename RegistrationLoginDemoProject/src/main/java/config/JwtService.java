package config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;

@Service
public class JwtService {

    private static final String SECRET_KEY = "";
    public String extractUserName(String token) {
        return null;
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()

                .setSigningKey(getSignKey())
                .build()
                        .parseClaimsJwt(token)
                .getBody();

    }

    private Key getSignKey() {
        byte[] KeyBytes = Decoders.BASE64.decode(SECRET_KEY);
    }
}
