package org.octocode.repositories;

import org.octocode.domain.Part;
import org.springframework.data.repository.CrudRepository;

public interface PartRepository extends CrudRepository<Part, Long> {
}
