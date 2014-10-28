package org.octocode.repositories;

import org.octocode.domain.Task;

import java.util.List;

public interface TaskRepositoryCustom {
    Task findOne(Long id);

    Task save(Task task);

//    Task findByEmailAuthor(Customer author);

    List<Task> findByTags(List<String> tags);
}
