package ai.ecma.appapplicationindividual.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * BY SIROJIDDIN on 30.11.2020
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;

    private String code;

    private Integer measurementId;

    private Double amount;
}
