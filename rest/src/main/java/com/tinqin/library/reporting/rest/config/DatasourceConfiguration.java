package com.tinqin.library.reporting.rest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = "com.tinqin.library.reporting.persistence.models")
@EnableJpaRepositories(basePackages = "com.tinqin.library.reporting.persistence.repositories")
@Configuration
@Profile("!test")
public class DatasourceConfiguration {
}
