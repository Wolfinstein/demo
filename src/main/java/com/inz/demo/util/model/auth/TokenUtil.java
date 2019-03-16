package com.inz.demo.util.model.auth;


import com.inz.demo.domain.User;
import com.inz.demo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Transactional
@Service
public class TokenUtil {

    private static final long VALIDITY_TIME_MS = (long) 2 * 60 * 60 * 1000; // 2 hours  validity
    private static final String AUTH_HEADER_NAME = "Authorization";
    private final UserRepository userRepository;
    private String secret = "mrin";

    public TokenUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<Authentication> verifyToken(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);

        if (token != null && !token.isEmpty()) {
            final TokenUser user = parseUserFromToken(token.replace("Bearer", "").trim());
            return Optional.of(new UserAuthentication(user));
        }
        return Optional.empty();

    }

    private TokenUser parseUserFromToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        User user = userRepository.getOne(Long.valueOf(String.valueOf(claims.get("userId"))));
        return new TokenUser(user);
    }

    public String createTokenForUser(TokenUser tokenUser) {
        return createTokenForUser(tokenUser.getUser());
    }

    private String createTokenForUser(User user) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .setSubject(user.getUserName() + user.getUserSurname())
                .claim("userId", user.getUserId())
                .claim("role", user.getRoles())
                .claim("login", user.getUserLogin())
                .claim("passwordHash", user.getUserPassword())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

}
