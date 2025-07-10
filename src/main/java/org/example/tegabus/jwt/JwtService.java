package org.example.tegabus.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.tegabus.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value("${jwt.secret_key}")
    private String SECRET_KEY;
    @Value("${jwt.expiration}")
    private Long EXPIRATION_TIME;

    public String generateToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        User user = (User) userDetails;
        return Jwts.builder()
                .subject(username)
                .claim("full_name", user.getFirstName() + user.getLastName())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ getExpirationTime()))
                .signWith(getKey())
                .compact();
    }
    //when getting all claims from a token
    public Claims getClaims (String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Extracting a single claim from token
    public <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }
    //this method extracts the username from token using get claim method
    public String getUsername(String token){
        return getClaims(token, Claims::getSubject);
    }
    //This method extracts the expiration from token using getClaim
    public Date getExpirationTime(String token){
        return getClaims(token, Claims::getExpiration);
    }
    public List<String> getRoles(String token){
        return getClaims(token, claims -> {
//            List<String> roles = new ArrayList<>();
            Object roles = claims.get("roles");
            if(roles instanceof List<?> roleList){
                return roleList.stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .collect(Collectors.toList());
            }
            return List.of();
        });
    }
    public SecretKey getKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public Long getExpirationTime(){
        return EXPIRATION_TIME;
    }
    public boolean isTokenExpired(String token){
        return getExpirationTime(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
