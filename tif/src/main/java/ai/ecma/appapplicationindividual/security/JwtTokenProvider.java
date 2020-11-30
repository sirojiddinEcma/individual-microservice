package ai.ecma.appapplicationindividual.security;

import ai.ecma.appapplicationindividual.entity.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * BY SIROJIDDIN on 06.11.2020
 */


@Component
public class JwtTokenProvider {
    static long durationLiveToken = 1000 * 60 * 60 * 24 * 7;
    static String kalitSuz = "buniHechkimgaAytma";

    public String generateToken(User user) {
        Date expireDate = new Date(new Date().getTime() + durationLiveToken);
        return Jwts
                .builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, kalitSuz)
                .compact();
    }

    public boolean validToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(kalitSuz)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expiredJwtException) {
            System.err.println("Muddati o'tgan");
        } catch (MalformedJwtException malformedJwtException) {
            System.err.println("Buzilgan token");
        } catch (IllegalArgumentException illegalArgumentException) {
            System.err.println("Bo'sh token");
        } catch (SignatureException signatureException) {
            System.err.println("Kalit so'z xato");
        }
        return false;
    }

    public String getUserId(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(kalitSuz)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

}
