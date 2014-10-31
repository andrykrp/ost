package org.octocode.repositories;

import org.octocode.domain.Part;
import org.octocode.domain.Tag;
import org.octocode.domain.Task;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
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

//    @Override
//    public Task findByEmailAuthor(Customer author) {
//        TypedQuery<Task> query = entityManager.createQuery("select t from Task t where t.author = :author", Task.class);
//        query.setParameter("author", author);
//        return query.getSingleResult();
//    }

    @Override
    public List<Task> findByTags(List<String> tags, List<String> orderGroups, List<String> orderFields) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);

        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<Task> entityType = metamodel.entity(Task.class);
        Root<Task> taskRoot = criteriaQuery.from(entityType);
        criteriaQuery.select(taskRoot);

        SetJoin<Task, Tag> join = taskRoot.joinSet("tags", JoinType.INNER);
        criteriaQuery.where(
                criteriaBuilder.in(join.get("name")).value(tags)
        );

//        SetJoin<Tag, Part> groupJoin = join.joinSet("tags", JoinType.LEFT);
//        criteriaQuery.where(
//                criteriaBuilder.in(join.get("name")).value(tags)
//        );

//        criteriaQuery.groupBy(taskRoot.get("id"));
//        criteriaQuery.having(criteriaBuilder.equal(criteriaBuilder.count(taskRoot), tags.size()));

        TypedQuery<Task> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }
}
