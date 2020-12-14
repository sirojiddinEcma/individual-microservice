package ai.ecma.organization.repository;

import ai.ecma.organization.entity.Role;
import ai.ecma.organization.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, RoleName> {
    Role findByRoleName(RoleName roleName);
}
