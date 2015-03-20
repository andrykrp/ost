package org.octocode.repositories;

import org.octocode.domains.Task;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TaskRepository extends Repository<Task, Long>, TaskRepositoryCustom {
    List<Task> findByTagsNameContains(List<String> tags);

//    Task findByAuthorEmail(Email email);
}
