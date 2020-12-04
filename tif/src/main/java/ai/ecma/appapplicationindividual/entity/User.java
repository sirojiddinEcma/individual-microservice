package ai.ecma.appapplicationindividual.entity;

import ai.ecma.appapplicationindividual.entity.enums.RoleName;
import ai.ecma.appapplicationindividual.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Collection;
import java.util.Collections;

/**
 * BY SIROJIDDIN on 23.11.2020
 */


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(length = 9, nullable = false, unique = true)
    private String tin;

    @Column(nullable = false, unique = true)
    private String keySerialNumber;

    private String companyName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    private String fax;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private RoleName role;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer personTypeId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority grantedAuthority = (GrantedAuthority) () -> role.name();
        return Collections.singleton(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return keySerialNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public User(String firstName, String lastName, String tin, String keySerialNumber, String companyName, String phoneNumber, String email, String fax, RoleName role, String password, Integer personTypeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tin = tin;
        this.keySerialNumber = keySerialNumber;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.fax = fax;
        this.role = role;
        this.password = password;
        this.personTypeId = personTypeId;
    }
}
