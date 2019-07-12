package ru.relex.restaurant.db;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("db.properties")
@EntityScan("ru.relex.restaurant.db.entity")
@EnableJpaRepositories("ru.relex.restaurant.db.JpaRepository")
public class DbConfiguration {
}
