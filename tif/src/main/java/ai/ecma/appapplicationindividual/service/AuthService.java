package ai.ecma.appapplicationindividual.service;

import ai.ecma.appapplicationindividual.component.MessageByLang;
import ai.ecma.appapplicationindividual.entity.User;
import ai.ecma.appapplicationindividual.entity.enums.RoleName;
import ai.ecma.appapplicationindividual.exception.ResourceNotFoundException;
import ai.ecma.appapplicationindividual.exception.UserNotFoundException;
import ai.ecma.appapplicationindividual.payload.ApiResponse;
import ai.ecma.appapplicationindividual.payload.LoginDto;
import ai.ecma.appapplicationindividual.payload.UserDto;
import ai.ecma.appapplicationindividual.repository.PersonTypeRepository;
import ai.ecma.appapplicationindividual.repository.UserRepository;
import ai.ecma.appapplicationindividual.security.JwtTokenProvider;
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
    PersonTypeRepository personTypeRepository;
    @Autowired
    MessageByLang messageByLang;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    public ApiResponse registerUser(UserDto userDto) {
        boolean exists = userRepository.existsByKeySerialNumber(userDto.getKeySerialNumber());
        if (exists)
            return new ApiResponse(
                    messageByLang.getMessageByKey("user.already.exist"),
                    false);
        User user = new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getTin(),
                userDto.getKeySerialNumber(),
                userDto.getCompanyName(),
                userDto.getPhoneNumber(),
                userDto.getEmail(),
                userDto.getFax(),
                personTypeRepository.findById(userDto.getPersonTypeId()).orElseThrow(() -> new ResourceNotFoundException("personType", "id", userDto.getPersonTypeId())),
                RoleName.ROLE_USER,
                passwordEncoder.encode(userDto.getKeySerialNumber())

        );
        userRepository.save(user);
        return new ApiResponse(
                messageByLang.getMessageByKey("user.success.registered"),
                true
        );
    }

    public ApiResponse login(LoginDto loginDto) {
        try {

            Authentication authenticate =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginDto.getLogin(),
                                    loginDto.getLogin()
                            ));
//            UserDetails userDetails = loadUserByUsername(loginDto.getLogin());
//            if (
//                    passwordEncoder.matches(loginDto.getLogin(), userDetails.getPassword())
//                            && userDetails.isAccountNonExpired()
//                            && userDetails.isAccountNonLocked()
//                            && userDetails.isCredentialsNonExpired()
//                            && userDetails.isEnabled()
//            )
            return new ApiResponse(
                    messageByLang.getMessageByKey("ok"),
                    true,
                    jwtTokenProvider.generateToken((User) authenticate.getPrincipal())
            );
//            else
//                return new ApiResponse(
//                        messageByLang.getMessageByKey("password.or.login.error"),
//                        false);
        } catch (Exception e) {
            return new ApiResponse(messageByLang.getMessageByKey("password.or.login.error"), false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByKeySerialNumber(s).orElseThrow(() -> new UserNotFoundException(s));
    }
}
