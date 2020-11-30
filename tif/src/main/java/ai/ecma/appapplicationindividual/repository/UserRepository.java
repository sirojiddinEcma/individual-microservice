package ai.ecma.appapplicationindividual.repository;

import ai.ecma.appapplicationindividual.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByKeySerialNumber(String keySerialNumber);

    Optional<User> findByKeySerialNumber(String keySerialNumber);
}
