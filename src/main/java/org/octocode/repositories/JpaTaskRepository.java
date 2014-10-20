package org.octocode.repositories;

import org.octocode.domain.Customer;
import org.octocode.domain.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class JpaTaskRepository implements TaskRepository {
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
    public Task findByEmailAuthor(Customer author) {
        TypedQuery<Task> query = entityManager.createQuery("select t from Task t where t.author = :author", Task.class);
        query.setParameter("author", author);
        return query.getSingleResult();
    }
}
