package pro.security.amg.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static pro.security.amg.security.ApplicatioUserPermission.*;
import static pro.security.amg.security.ApplicationUserRole.*;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/css", "/js")
                .permitAll()
/*
                .antMatchers(HttpMethod.GET,"/api/person/get/**").hasAuthority(PERSON_READ.getPermission())
                .antMatchers(HttpMethod.GET,"/api/person/**").hasAuthority(PERSON_WRITE.getPermission())
                .antMatchers(HttpMethod.GET,"/api/intern/get/**").hasAuthority(INTERN_READ.getPermission())
                .antMatchers(HttpMethod.GET,"/api/intern/**").hasAuthority(INTERN_WRITE.getPermission())
                */
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
             .loginPage("/login")
             .permitAll()
               .defaultSuccessUrl("/welcome", true)
               .passwordParameter("password")
                .usernameParameter("username")
        .and()
        .rememberMe()
       .tokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(10))
       .key("john-lennon")
                .rememberMeParameter("remember-me")
          .and()
        .logout()
        .logoutUrl("/logout")
     //    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //csrf disable
       .clearAuthentication(true)
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID","remember-me")
         .logoutSuccessUrl("/login")
     /*  */
        ;

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails doctor = User
                .builder()
                .username("doctor")
                .password(passwordEncoder.encode("doctor"))
          //      .roles(DOCTOR.name())   // ROLE_DOCTOR
            .authorities(DOCTOR.getGrantedAuthorities())
                .build();

        UserDetails admin = User
                .builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
              //  .roles(ADMIN.name())   // ROLE_ADMIN
               .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails person = User
                .builder()
                .username("person")
                .password(passwordEncoder.encode("person"))
              //  .roles(PERSON.name())   //ROLE_PERSON
                .authorities(PERSON.getGrantedAuthorities())
                .build();

        UserDetails intern = User
                .builder()
                .username("intern")
                .password(passwordEncoder.encode("intern"))
             //   .roles(INTERN.name())   //ROLE_INTERN
                .authorities(INTERN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                doctor,
                admin,
                person,
                intern
        );
    }



}
