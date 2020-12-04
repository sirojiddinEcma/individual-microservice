package ai.ecma.appapplicationindividual.service;

import ai.ecma.appapplicationindividual.entity.Organization;
import ai.ecma.appapplicationindividual.payload.ApiResponse;
import ai.ecma.appapplicationindividual.payload.OrganizationDto;
import ai.ecma.appapplicationindividual.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * BY SIROJIDDIN on 04.12.2020
 */


@Service
public class OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse add(OrganizationDto organizationDto) {
        organizationRepository.save(new Organization(
                organizationDto.getName(),
                organizationDto.getTin(),
                passwordEncoder.encode(organizationDto.getPassword())
        ));
        return new ApiResponse("Saqlandi", true);
    }
}
