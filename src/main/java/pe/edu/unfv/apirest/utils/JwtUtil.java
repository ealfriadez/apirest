package pe.edu.unfv.apirest.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pe.edu.unfv.apirest.models.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey key = Keys.hmacShaKeyFor(
            "80c68e5fb9341a6280c4f4867a190d35439c0a01972f22c7f920ce99600e8e7c5d5fc28c"
                    .getBytes(StandardCharsets.UTF_8)
    );

    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public String extractUserName(String token){
        return getClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String generateToken(User user){
        long expirationMills = 100 * 60 * 60 * 24;
        Date now = new Date();
        Date expiry = new Date(now.getTime()  + expirationMills);

        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key)
                .compact();
    }
}
