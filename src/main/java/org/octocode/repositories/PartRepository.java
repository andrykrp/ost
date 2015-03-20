package org.octocode.repositories;

import org.octocode.domains.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PartRepository extends CrudRepository<Part, Long> {
}
