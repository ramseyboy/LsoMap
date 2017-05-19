package me.ramseyboy.exchange.api

import com.bedatadriven.jackson.datatype.jts.JtsModule
import com.fasterxml.jackson.databind.Module
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter

@SpringBootApplication
class TelecomExchangeApi {

    @Bean
    fun geoJsonModule(): Module {
        return JtsModule()
    }
}

@Configuration
class ApiRepositoryConfiguration : RepositoryRestConfigurerAdapter() {

    companion object {
        private val DEFAULT_PAGE_SIZE = 30
    }

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?) {
        config?.let { c ->
            c.defaultPageSize = DEFAULT_PAGE_SIZE
            c.maxPageSize = DEFAULT_PAGE_SIZE
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(TelecomExchangeApi::class.java, *args)
}


