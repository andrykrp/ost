package org.octocode.repositories;

import org.octocode.domain.Customer;
import org.octocode.domain.Task;

import java.util.List;
import java.util.Set;

public interface TaskRepositoryCustom {
    Task findOne(Long id);

    Task save(Task task);

    Task findByEmailAuthor(Customer author);

    List<Task> findByTags(List<String> tags);
}
