package ai.ecma.organization.security;

import ai.ecma.organization.entity.User;
import ai.ecma.organization.repository.OrganizationRepository;
import ai.ecma.organization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    OrganizationRepository organizationRepository;

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
        UserDetails userDetails = null;
        if (token.startsWith("Bearer "))
            userDetails = getUserFromBearerToken(token);
        else if (token.startsWith("Basic "))
            userDetails = getUserFromBasicToken(token);
        if (userDetails != null && userDetails.isEnabled() && userDetails.isAccountNonExpired() && userDetails.isAccountNonLocked() && userDetails.isCredentialsNonExpired()) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
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

    public UserDetails getUserFromBasicToken(String token) {
        token = token.substring("Basic".length()).trim();
        byte[] decode = Base64.getDecoder().decode(token);
        token = new String(decode, Charset.defaultCharset());
        String[] split = token.split(":", 2);
//        Optional<Organization> optionalOrganization = organizationRepository.findByTin(split[0]);
//        if (optionalOrganization.isPresent()) {
//            Organization organization = optionalOrganization.get();
//            if (passwordEncoder.matches(split[1], organization.getPassword()))
//                return organization;
//        }
        return null;
    }
}
