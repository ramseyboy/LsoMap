package me.ramseyboy.exchange.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class ApiRepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    private static final int DEFAULT_PAGE_SIZE = 30;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setDefaultPageSize(DEFAULT_PAGE_SIZE);
        config.setMaxPageSize(DEFAULT_PAGE_SIZE);
    }
}