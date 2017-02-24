package me.ramseyboy.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class TelecomExchangeApp {

    public static void main(String[] args) {
        SpringApplication.run(TelecomExchangeApp.class, args);
    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}