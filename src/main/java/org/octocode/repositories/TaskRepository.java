package org.octocode.repositories;

import org.octocode.domain.Customer;
import org.octocode.domain.Email;
import org.octocode.domain.Tag;
import org.octocode.domain.Task;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TaskRepository extends Repository<Task, Long>, TaskRepositoryCustom {
    List<Task> findByTagsNameContains(List<String> tags);
    Task findByAuthorEmail(Email email);
}
