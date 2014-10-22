package org.octocode.repositories;

import org.octocode.domain.Customer;
import org.octocode.domain.Task;

public interface TaskRepositoryCustom {
    Task findOne(Long id);

    Task save(Task task);

    Task findByEmailAuthor(Customer author);
}
