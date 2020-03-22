package pro.security.amg.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static pro.security.amg.security.ApplicationUserRole.*;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/css", "/js")
                .permitAll()
                //.antMatchers("/api/intern/get/**").hasAnyRole(DOCTOR_ROLE.name(), ADMIN_ROLE.name())


                .antMatchers("/api/person/get/**").hasAnyRole(INTERN_ROLE.name(), DOCTOR_ROLE.name(), ADMIN_ROLE.name())
                .antMatchers("/api/person/**").hasAnyRole(DOCTOR_ROLE.name(), ADMIN_ROLE.name())

             .antMatchers("/api/intern/get/**").hasAnyRole(DOCTOR_ROLE.name(), ADMIN_ROLE.name())

              //  .antMatchers("/api/person/**").hasAnyRole(DOCTOR_ROLE.name(), ADMIN_ROLE.name())

                .antMatchers("/api/**").hasRole(ADMIN_ROLE.name())




                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
        ;
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails doctor = User
                .builder()
                .username("doctor")
                .password(passwordEncoder.encode("doctor"))
                .roles(DOCTOR_ROLE.name())
                .build();

        UserDetails admin = User
                .builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(ADMIN_ROLE.name())
                .build();

        UserDetails person = User
                .builder()
                .username("person")
                .password(passwordEncoder.encode("person"))
                .roles(PERSON_ROLE.name())
                .build();

        UserDetails intern = User
                .builder()
                .username("intern")
                .password(passwordEncoder.encode("intern"))
                .roles(INTERN_ROLE.name())
                .build();

        return new InMemoryUserDetailsManager(
                doctor,
                admin,
                person,
                intern
        );
    }



}
