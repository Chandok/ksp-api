package com.ks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class KhalsaSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(KhalsaSchoolApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/course").allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");

                registry.addMapping("/course/list").allowedOrigins("http://localhost:4200");
                registry.addMapping("/course/create").allowedOrigins("http://localhost:4200");


                registry.addMapping("/class").allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
                registry.addMapping("/class/create").allowedOrigins("http://localhost:4200");
            }
        };
    }

}
