package ai.ecma.appapplicationindividual.entity;

import ai.ecma.appapplicationindividual.entity.template.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * BY SIROJIDDIN on 23.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {
    private String name;

    @Column(length = 10)
    private String code;

    @Column(nullable = false)
    private Integer measurementId;

    private Double amount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Application application;
}
