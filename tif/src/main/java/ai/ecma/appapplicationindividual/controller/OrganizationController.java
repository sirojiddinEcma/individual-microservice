package ai.ecma.appapplicationindividual.controller;

import ai.ecma.appapplicationindividual.payload.ApiResponse;
import ai.ecma.appapplicationindividual.payload.OrganizationDto;
import ai.ecma.appapplicationindividual.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BY SIROJIDDIN on 04.12.2020
 */


@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    public HttpEntity<?> add(@RequestBody OrganizationDto organizationDto) {
        ApiResponse apiResponse = organizationService.add(organizationDto);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
}
