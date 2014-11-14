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
                Task t = entityManager.merge(task);
            return t;
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
        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);

        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<Task> entityType = metamodel.entity(Task.class);
        Root<Task> taskRoot = criteriaQuery.from(entityType);
        criteriaQuery.select(taskRoot);

        SetJoin<Task, Tag> join = taskRoot.joinSet("tags", JoinType.INNER);
        criteriaQuery.where(
                criteriaBuilder.in(join.get("name")).value(tags)
        );

        criteriaQuery.groupBy(taskRoot.get("id"));
        criteriaQuery.having(criteriaBuilder.equal(criteriaBuilder.count(taskRoot), tags.size()));

        for (int i = 1; i <= 3; i++) {
            SetJoin<Tag, Part> groupJoin = join.joinSet("parts", JoinType.LEFT);
            ParameterExpression<String> pName = criteriaBuilder.parameter(String.class, ("grp_p" + i));
            criteriaQuery.where(
//                criteriaBuilder.equal(groupJoin.get("name"), pName)
                    criteriaBuilder.like(criteriaBuilder.lower(groupJoin.<String>get("name")), pName)
            );
        }

        TypedQuery<Task> typedQuery = entityManager.createQuery(criteriaQuery);
        for (int i = 1; i <= 3; i++) {
            typedQuery.setParameter("grp_p" + i, "group" + i);
        }

        return typedQuery.getResultList();*/


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);

        Root<Task> from = criteriaQuery.from(Task.class);
        CriteriaQuery<Task> select = criteriaQuery.select(from);

        Path<Task> path = from.get("id");

        Subquery<Task> subquery = criteriaQuery.subquery(Task.class);
        Root<Task> fromProject = subquery.from(Task.class);

        subquery.select(fromProject);
        SetJoin<Task, Tag> join = fromProject.joinSet("tags", JoinType.INNER);
        subquery.where(
                join.get("name").in(tags)
        );
        subquery.groupBy(fromProject.get("id"));
        subquery.having(criteriaBuilder.equal(criteriaBuilder.count(fromProject), tags.size()));

        select.where(criteriaBuilder.in(path).value(subquery));

        TypedQuery<Task> typedQuery = entityManager.createQuery(select);
        return typedQuery.getResultList();


        /*

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);

Root<Task> from = criteriaQuery.from(Task.class);
CriteriaQuery<Task> select = criteriaQuery.select(from);

Path<Task> path = from.get("id");

Subquery<Task> subquery = criteriaQuery.subquery(Task.class);
Root<Task> fromProject = subquery.from(Task.class);

subquery.select(fromProject);
SetJoin<Task, Tag> join = fromProject.joinSet("tags", JoinType.INNER);
subquery.where(
        join.get("name").in(tags)
);
subquery.groupBy(fromProject.get("id"));
subquery.having(criteriaBuilder.equal(criteriaBuilder.count(fromProject), tags.size()));

SetJoin<Task, Tag> tagJoin = from.joinSet("tags", JoinType.INNER);
SetJoin<Tag, Part> partJoin = tagJoin.joinSet("parts", JoinType.LEFT);

//select.where();
select.where(criteriaBuilder.and(criteriaBuilder.in(path).value(subquery),criteriaBuilder.like(criteriaBuilder.lower(partJoin.<String>get("name")), criteriaBuilder.parameter(String.class, ("partName")))));

TypedQuery<Task> typedQuery = entityManager.createQuery(select);
typedQuery.setParameter("partName", "group-1");
typedQuery.getResultList();

        */
    }
}

