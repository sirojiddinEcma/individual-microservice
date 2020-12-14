package ai.ecma.organization.service;

import ai.ecma.organization.entity.User;
import ai.ecma.organization.payload.ApiResponse;
import ai.ecma.organization.payload.LoginDto;
import ai.ecma.organization.repository.UserRepository;
import ai.ecma.organization.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * BY SIROJIDDIN on 27.11.2020
 */


@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    public ApiResponse login(LoginDto loginDto) {
        try {

            Authentication authenticate =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginDto.getLogin(),
                                    loginDto.getLogin()
                            ));

            return new ApiResponse(
                    "ok",
                    true,
                    jwtTokenProvider.generateToken((User) authenticate.getPrincipal())
            );
        } catch (Exception e) {
            return new ApiResponse("password.or.login.error", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByKeySerialNumber(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
