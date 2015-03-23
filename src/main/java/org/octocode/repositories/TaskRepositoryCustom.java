package org.octocode.repositories;

import org.octocode.domains.Task;

import java.util.List;

public interface TaskRepositoryCustom {
    Task findOne(Long id);

    Task save(Task task);

//    Task findByEmailAuthor(Customer author);

    List findByTags(String sql);
}
