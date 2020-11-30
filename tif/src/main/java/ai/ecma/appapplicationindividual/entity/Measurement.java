package ai.ecma.appapplicationindividual.entity;

import ai.ecma.appapplicationindividual.entity.template.AbsNameEntity;
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
