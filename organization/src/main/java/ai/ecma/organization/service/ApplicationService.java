package ai.ecma.organization.service;

import ai.ecma.organization.entity.User;
import ai.ecma.organization.payload.ApiResponse;
import ai.ecma.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * BY SIROJIDDIN on 04.12.2020
 */


@Service
public class ApplicationService {
    @Autowired
    RestTemplate restTemplate;

    public ApiResponse getApplications(int page, int size, User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setBasicAuth(user.getOrganization().getTin(), user.getOrganization().getTin());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AppConstants.TIF_URL)
                .queryParam("page", page)
                .queryParam("size", size);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<ApiResponse> exchange = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ApiResponse.class);
        return exchange.getBody();
    }
}
