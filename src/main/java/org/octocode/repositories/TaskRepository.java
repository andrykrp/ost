package org.octocode.repositories;

import org.octocode.domain.Tag;
import org.octocode.domain.Task;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TaskRepository extends Repository<Task, Long>, TaskRepositoryCustom {
    List<Task> findByTagsNameIn(List<String> tags);
}
