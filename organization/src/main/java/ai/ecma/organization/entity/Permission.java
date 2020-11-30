package ai.ecma.organization.entity;

import ai.ecma.organization.entity.enums.PermissionEnum;
import ai.ecma.organization.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

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
public class Permission extends AbsNameEntity implements GrantedAuthority {
    @Enumerated(EnumType.STRING)
    private PermissionEnum permissionEnum;

    @Override
    public String getAuthority() {
        return permissionEnum.name();
    }
}
