package pl.sda.restspringbooks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .realmName("Books App")
                .and()
                .csrf()
                .disable()
                .headers()
                .and()
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/api/v1/**").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        final UserDetails ewa = User
                .builder()
                .password(passwordEncoder().encode("1234"))
                .username("ewa")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .authorities("ROLE_ADMIN", "ROLE_USER")
                .build();
        return new InMemoryUserDetailsManager(ewa);
    }
}
