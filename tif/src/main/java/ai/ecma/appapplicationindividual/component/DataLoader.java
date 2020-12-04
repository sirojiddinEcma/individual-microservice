package ai.ecma.appapplicationindividual.component;

import ai.ecma.appapplicationindividual.entity.User;
import ai.ecma.appapplicationindividual.entity.enums.RoleName;
import ai.ecma.appapplicationindividual.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * BY SIROJIDDIN on 04.12.2020
 */


@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.datasource.initialization-mode}")
    private String modeType;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (modeType.equals("always")) {
            userRepository.save(new User(
                    "Admin",
                    "Adminov",
                    "123456789",
                    "1234567890",
                    "TIF",
                    "9989",
                    "admin@tif.uz",
                    "",
                    RoleName.ROLE_ADMIN,
                    passwordEncoder.encode("1234567890"),
                    2
            ));
        }
    }
}
