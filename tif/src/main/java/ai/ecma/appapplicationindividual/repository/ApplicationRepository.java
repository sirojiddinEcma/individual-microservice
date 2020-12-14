package ai.ecma.appapplicationindividual.repository;

import ai.ecma.appapplicationindividual.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {

    @Query(nativeQuery = true, value = "select count(*) from application where created_at>=date_trunc('year',current_date)\n" +
            " and created_at<date_trunc('year',current_date+interval '1' year)")
    int countCurrentYear();

    Page<Application> findAllByOrganizationId(Integer organizationId, Pageable pageable);
}
