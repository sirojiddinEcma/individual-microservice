package ai.ecma.organization.entity;

import ai.ecma.organization.entity.template.AbsNameEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * BY SIROJIDDIN on 23.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Measurement extends AbsNameEntity {

}
