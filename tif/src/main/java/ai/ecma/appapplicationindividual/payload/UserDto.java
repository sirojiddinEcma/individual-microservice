package ai.ecma.appapplicationindividual.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * BY SIROJIDDIN on 27.11.2020
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(max = 9, min = 9)
    private String tin;

    @NotBlank
    private String keySerialNumber;

    @NotBlank
    private String companyName;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    private String fax;

    @NotNull
    private Integer personTypeId;
}
