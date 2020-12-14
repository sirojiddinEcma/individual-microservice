package ai.ecma.organization.controller;

import ai.ecma.organization.payload.ApiResponse;
import ai.ecma.organization.payload.LoginDto;
import ai.ecma.organization.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * BY SIROJIDDIN on 27.11.2020
 */


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse response = authService.login(loginDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 401).body(response);
    }
}
