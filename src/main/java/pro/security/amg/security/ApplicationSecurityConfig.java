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
                //.antMatchers("/api/intern/get/**").hasAnyRole(DOCTOR .name(), ADMIN .name())


             .antMatchers("/api/person/get/**").hasAnyRole(INTERN.name(), DOCTOR.name(), ADMIN.name())
             .antMatchers("/api/person/**").hasAnyRole(DOCTOR.name(), ADMIN.name())
             .antMatchers("/api/intern/get/**").hasAnyRole(DOCTOR.name(), ADMIN.name())

              //  .antMatchers("/api/person/**").hasAnyRole(DOCTOR .name(), ADMIN .name())

                .antMatchers("/api/**").hasRole(ADMIN.name())




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
                .roles(DOCTOR.name())   // ROLE_DOCTOR
                .build();

        UserDetails admin = User
                .builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(ADMIN.name())   // ROLE_ADMIN
                .build();

        UserDetails person = User
                .builder()
                .username("person")
                .password(passwordEncoder.encode("person"))
                .roles(PERSON.name())   //ROLE_PERSON
                .build();

        UserDetails intern = User
                .builder()
                .username("intern")
                .password(passwordEncoder.encode("intern"))
                .roles(INTERN.name())   //ROLE_INTERN
                .build();

        return new InMemoryUserDetailsManager(
                doctor,
                admin,
                person,
                intern
        );
    }



}
