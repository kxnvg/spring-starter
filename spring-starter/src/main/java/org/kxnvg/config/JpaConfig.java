package org.kxnvg.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JpaConfig {

    @Bean
    @ConfigurationProperties(prefix = "database")
    public DatabaseProperties databaseProperties() {
        return new DatabaseProperties();
    }

    @PostConstruct
    void init() {
        log.info("Jpa configuration is enabled");
    }
}
