package ai.ecma.organization.entity;

import ai.ecma.organization.entity.enums.PersonTypeEnum;
import ai.ecma.organization.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * BY SIROJIDDIN on 23.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersonType extends AbsNameEntity {
    @Enumerated(value = EnumType.STRING)
    private PersonTypeEnum personTypeEnum;
}
