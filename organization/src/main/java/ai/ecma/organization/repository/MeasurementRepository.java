package ai.ecma.organization.repository;

import ai.ecma.organization.entity.Measurement;
import ai.ecma.organization.entity.Organization;
import ai.ecma.organization.projection.CustomOrganization;
import ai.ecma.organization.projection.CustomProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


@RepositoryRestResource(path = "measurement", collectionResourceRel = "list",excerptProjection = CustomProjection.class)
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
