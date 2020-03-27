package pro.security.amg.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pro.security.amg.auth.ApplicationUserService;

import java.util.concurrent.TimeUnit;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {

        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
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
/*  */              .and()
                .rememberMe()
                .tokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(10))
                .key("john-lennon")
                .rememberMeParameter("remember-me")
                .and()
                .logout()
                .logoutUrl("/logout")
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // if csrf disable
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID","remember-me")
                .logoutSuccessUrl("/login")
        /*    */
        ;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return  provider;
    }
}
