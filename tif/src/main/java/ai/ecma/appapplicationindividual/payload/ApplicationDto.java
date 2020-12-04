package ai.ecma.appapplicationindividual.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * BY SIROJIDDIN on 30.11.2020
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {

    private Integer organizationId;

    private List<ProductDto> productDtoList;


    private List<UUID> attachmentIds;
}
