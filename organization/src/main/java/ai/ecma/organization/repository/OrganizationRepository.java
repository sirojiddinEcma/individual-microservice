package ai.ecma.organization.repository;

import ai.ecma.organization.entity.Organization;
import ai.ecma.organization.projection.CustomOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


@RepositoryRestResource(path = "organization", collectionResourceRel = "list",excerptProjection = CustomOrganization.class)
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
