package com.zisal.security.springbootjwtsecurity.util;

import com.zisal.security.springbootjwtsecurity.dto.JwtUserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 5/14/18.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class JwtTokenUtil implements Serializable{

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.header}")
    private String jwtHeader;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    private final String AUDIENCE_UNKNOWN = "unknown";
    private final String AUDIENCE_WEB = "web";
    private final String AUDIENCE_MOBILE = "mobile";
    private final String AUDIENCE_TABLET = "tablet";

    public String getUserNameFromToken(String p_Token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(p_Token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String p_Token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(p_Token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Date getCreatedDateFromToken(String p_Token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(p_Token);
            created = new Date((Long) claims.get("created"));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String p_Token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(p_Token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public void setTokenToBeExpired(String token) {
        Claims claims = this.getClaimsFromToken(token);
        System.err.println("Expiration "+claims.getExpiration());
        Date reverseExpiration = generateReverseExpirationDate(token);
        System.err.println("Expiration set To "+reverseExpiration);
        claims.setExpiration(reverseExpiration);
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            audience = (String) claims.get("audience");
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.jwtExpiration * 1000);
    }

    private Date generateReverseExpirationDate(String p_Token) {
        return new Date(getCreatedDateFromToken(p_Token).getTime() - this.jwtExpiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        final Date current = this.generateCurrentDate();
        return expiration.before(current);
    }

    private String generateAudience(Device device) {
        String audience = this.AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = this.AUDIENCE_WEB;
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;
        }
        return audience;
    }

    private Boolean ignoreTokenExpiration(String token) {
        String audience = this.getAudienceFromToken(token);
        return (this.AUDIENCE_TABLET.equals(audience) || this.AUDIENCE_MOBILE.equals(audience));
    }

    public String generateToken(UserDetails userDetails, Device device) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("audience", this.generateAudience(device));
        claims.put("created", this.generateCurrentDate());
        return this.generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        Date expired = this.generateExpirationDate();
        return Jwts.builder().setClaims(claims).setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !(this.isTokenExpired(token)));
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public static String generateToken(JwtUserDTO u, String secret) {
        Claims claims = Jwts.claims().setSubject(u.getUserName());
        claims.put("userName", u.getUserName());
        claims.put("role", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        JwtUserDTO user = new JwtUserDTO();
        user.setUserName("User");
        user.setRole("ROLE_ADMIN");

        System.out.println("**************************************\n\n" + generateToken(user, "my-very-secret-key") + "\n\n**************************************");
    }
}
