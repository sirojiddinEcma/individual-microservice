package ai.ecma.appapplicationindividual.repository;

import ai.ecma.appapplicationindividual.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    Optional<Organization> findByTin(String tin);
}
