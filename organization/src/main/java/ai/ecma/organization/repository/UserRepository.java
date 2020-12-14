package ai.ecma.organization.repository;

import ai.ecma.organization.entity.Organization;
import ai.ecma.organization.entity.User;
import ai.ecma.organization.projection.CustomOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.UUID;

/**
 * BY SIROJIDDIN on 03.12.2020
 */


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByKeySerialNumber(String s);
}
