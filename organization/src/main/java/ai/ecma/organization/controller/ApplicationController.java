package ai.ecma.organization.controller;

import ai.ecma.organization.entity.User;
import ai.ecma.organization.payload.ApiResponse;
import ai.ecma.organization.security.CurrentUser;
import ai.ecma.organization.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * BY SIROJIDDIN on 04.12.2020
 */


@RestController
@RequestMapping("/api/application")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;

    @GetMapping
    public HttpEntity<?> getApplications(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size,
                                         @CurrentUser User user) {
        ApiResponse response = applicationService.getApplications(page, size,user);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }
}
