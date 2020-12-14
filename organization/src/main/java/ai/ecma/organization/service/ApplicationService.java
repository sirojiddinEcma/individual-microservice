package ai.ecma.organization.service;

import ai.ecma.organization.entity.User;
import ai.ecma.organization.payload.ApiResponse;
import ai.ecma.organization.payload.AppDto;
import ai.ecma.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AppConstants.TIF_URL + "/application")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("orgId", user.getOrganization().getId());

        HttpEntity<?> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<ApiResponse> exchange = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    ApiResponse.class);
            ApiResponse res = exchange.getBody();
            List<AppDto> appDto = (List<AppDto>) res.getObject();
            return new ApiResponse("Ok",true,appDto);
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == 403) {
                return new ApiResponse("Parol xato", false);
            } else {
                return new ApiResponse("Serverda xatolik", false);
            }
        }
    }
}
