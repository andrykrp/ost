package org.octocode.repositories;

import org.octocode.domain.Tag;
import org.octocode.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
