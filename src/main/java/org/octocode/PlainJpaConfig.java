package org.octocode;

import org.octocode.domain.Task;
import org.octocode.repositories.TaskRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ComponentScan(basePackageClasses = {Task.class, TaskRepository.class})
@ComponentScan
@Import(InfrastructureConfig.class)
public class PlainJpaConfig {

}