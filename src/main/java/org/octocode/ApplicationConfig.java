package org.octocode;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
//@EnableJpaRepositories (basePackageClasses = TagRepository.class)
@EnableJpaRepositories
@Import(InfrastructureConfig.class)
public class ApplicationConfig {
}
