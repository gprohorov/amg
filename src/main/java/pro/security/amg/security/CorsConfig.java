package pro.security.amg.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
         .allowedHeaders("*")
          .allowedMethods("*")
          .allowedOrigins("*")
        .allowedOrigins("http://localhost:4200")
      //  .allowedOrigins("http://localhost:8080")
      //    .allowCredentials(true)
               .exposedHeaders("Authorization")

 /*        */;

    }
}
