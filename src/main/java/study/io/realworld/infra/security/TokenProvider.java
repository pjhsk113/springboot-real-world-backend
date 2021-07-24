package study.io.realworld.infra.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Optional;

public class TokenProvider {
    private final String signKey;
    private final String token;

    public TokenProvider(String signKey, String token) {
        this.signKey = signKey;
        this.token = token;
    }

    public Optional<String> parseToken() {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signKey).parseClaimsJws(token);
            return Optional.of(claimsJws.getBody().getSubject());
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new BadCredentialsException("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            throw new BadCredentialsException("Expired JWT Token", e);
        }
    }
}
