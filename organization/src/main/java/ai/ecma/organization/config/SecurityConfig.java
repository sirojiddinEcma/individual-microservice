package ai.ecma.organization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

/**
 * BY SIROJIDDIN on 03.12.2020
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/organization/**", "/api/measurement/**")
                .permitAll()
                .antMatchers("/api/**")
                .authenticated();
    }
}
