package org.octocode.config;

import org.octocode.domain.Task;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = Task.class)
@Import(InfrastructureConfig.class)
public class PlainJpaConfig {
}
