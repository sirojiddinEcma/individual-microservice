package ai.ecma.appapplicationindividual.controller;

import ai.ecma.appapplicationindividual.entity.User;
import ai.ecma.appapplicationindividual.payload.ApiResponse;
import ai.ecma.appapplicationindividual.payload.ApplicationDto;
import ai.ecma.appapplicationindividual.security.CurrentUser;
import ai.ecma.appapplicationindividual.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * BY SIROJIDDIN on 30.11.2020
 */


@RestController
@RequestMapping("/api/application")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;

    @PostMapping
    public HttpEntity<?> sendApplication(@RequestBody ApplicationDto applicationDto, @CurrentUser User client) {
        ApiResponse apiResponse = applicationService.sendApplication(applicationDto, client);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @GetMapping
    public HttpEntity<?> getApplications(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size,
                                         @CurrentUser UserDetails user) {
        ApiResponse apiResponse = applicationService.getApplications(page, size, user);
        return ResponseEntity.ok(apiResponse);
    }


}
