package org.octocode.repositories;

import org.octocode.domains.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TagRepository extends CrudRepository<Tag, Long> {
}
