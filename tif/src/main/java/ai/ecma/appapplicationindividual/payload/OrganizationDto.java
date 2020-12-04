package ai.ecma.appapplicationindividual.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BY SIROJIDDIN on 04.12.2020
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private String name;

    private String tin;

    private String password;
}
