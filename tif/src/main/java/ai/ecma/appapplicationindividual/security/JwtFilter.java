package ai.ecma.appapplicationindividual.security;

import ai.ecma.appapplicationindividual.entity.User;
import ai.ecma.appapplicationindividual.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

/**
 * BY SIROJIDDIN on 06.11.2020
 */

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null)
            setAuthentication(token);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    public void setAuthentication(String token) {
        User user = null;
        if (token.startsWith("Bearer "))
            user = getUserFromBearerToken(token);
        else if (token.startsWith("Basic "))
//            user = getUserFromBasicToken(token);
        if (user != null&&user.isEnabled()&&user.isAccountNonExpired()&&user.isAccountNonLocked()&&user.isCredentialsNonExpired()) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    public User getUserFromBearerToken(String token) {
        token = token.substring(7);
        if (jwtTokenProvider.validToken(token)) {
            String userId = jwtTokenProvider.getUserId(token);
            Optional<User> optionalUser = userRepository.findById(UUID.fromString(userId));
            return optionalUser.orElse(null);
        }
        return null;
    }

//    public User getUserFromBasicToken(String token) {
//        token = token.substring("Basic".length()).trim();
//        byte[] decode = Base64.getDecoder().decode(token);
//        token = new String(decode, Charset.defaultCharset());
//        String[] split = token.split(":", 2);
//        Optional<User> optionalUser = userRepository.findByPhoneNumber(split[0]);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            if (passwordEncoder.matches(split[1], user.getPassword()))
//                return optionalUser.get();
//        }
//        return null;
//    }
}
