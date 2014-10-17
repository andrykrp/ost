package org.octocode.repositories;

import org.octocode.domain.Customer;
import org.octocode.domain.Task;
import org.springframework.data.repository.Repository;

public interface TaskRepository extends Repository<Task, Long> {
    Task findOne(Long id);

    Task save(Task task);

    Task findByEmailAuthor(Customer author);

}
