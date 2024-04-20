package by.juanjo.jitter.rest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("by.juanjo.jitter.core.entity")
@ComponentScan("by.juanjo.jitter.core.mapper")
@EnableJpaRepositories(basePackages = { "by.juanjo.jitter.core.repository" })
public class SpringConfig {
}
