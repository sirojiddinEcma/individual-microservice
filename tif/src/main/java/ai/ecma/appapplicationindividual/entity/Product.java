package ai.ecma.appapplicationindividual.entity;

import ai.ecma.appapplicationindividual.entity.template.AbsEntity;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Measurement measurement;

    private Double amount;
}
