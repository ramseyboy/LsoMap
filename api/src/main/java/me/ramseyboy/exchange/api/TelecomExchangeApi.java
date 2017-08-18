package me.ramseyboy.exchange.api;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TelecomExchangeApi {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TelecomExchangeApi.class, args);
    }

    @Bean
    public Module geoJsonModule() {
        return new JtsModule();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/switch")
                        .allowedOrigins("http://localhost:8080");
                registry.addMapping("/areacode")
                        .allowedOrigins("http://localhost:8080");
            }
        };
    }
}
