package ai.ecma.organization.component;

import ai.ecma.organization.entity.User;
import ai.ecma.organization.entity.enums.RoleName;
import ai.ecma.organization.repository.OrganizationRepository;
import ai.ecma.organization.repository.RoleRepository;
import ai.ecma.organization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

/**
 * BY SIROJIDDIN on 14.12.2020
 */


@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.datasource.initialization-mode}")
    private String modeType;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    RoleRepository roleRespository;

    @Override
    public void run(String... args) throws Exception {
        if (modeType.equals("always")) {
            try {
            User user = new User(
                    "UzStadartXodim",
                    "UzStadartXodim",
                    "123456789",
                    "1234567890",
                    organizationRepository.getOne(1),
                    "9989",
                    "admin@tif.uz",
                    passwordEncoder.encode("1234567890"),
                    Collections.singleton(roleRespository.findByRoleName(RoleName.ROLE_ORG_ADMIN))
            );
            userRepository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            }
        }
    }
}
