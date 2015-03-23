package org.octocode.repositories;

import org.octocode.domains.Task;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Task findOne(Long id) {
        return entityManager.find(Task.class, id);
    }

    @Override
    @Transactional
    public Task save(Task task) {
        if (task.getId() == null) {
            entityManager.persist(task);
            entityManager.flush();
            return task;
        } else {
            return entityManager.merge(task);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findByTags(String sql) {
        System.out.println("SQL: " + sql);
        return entityManager.createNativeQuery(sql, Task.class).getResultList();
    }
}

