package pl.sda.clinicapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.sda.clinicapi.repository.UsersRepository;
import pl.sda.clinicapi.service.UsersProvider;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultFilter(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // don't do that in prod
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/api/signup/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    @Profile("!test")
    public UserDetailsService defaultUserDetailsService(UsersRepository usersRepository) {
        return new UsersProvider(usersRepository);
    }

    @Bean
    @Profile("test")
    public UserDetailsService testUserDetailsService(PasswordEncoder passwordEncoder) {

        String pass = passwordEncoder.encode("pass");

        UserDetails doctor = User.withUsername("doctor")
                .password(pass)
                .roles("DOCTOR")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(pass)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(doctor, admin);
    }

}
