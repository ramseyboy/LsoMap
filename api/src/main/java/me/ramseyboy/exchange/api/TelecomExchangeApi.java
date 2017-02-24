package me.ramseyboy.exchange.api;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TelecomExchangeApi {

    public static void main(String[] args) {
        SpringApplication.run(TelecomExchangeApi.class, args);
    }

    @Bean
    public Module geoJsonModule() {
        return new JtsModule();
    }
}
