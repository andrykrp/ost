SELECT distinct t.* FROM Task t
INNER JOIN task_tag tags1_ ON t.id=tags1_.task_id
INNER JOIN Tag tag2_ ON tags1_.tag_id=tag2_.id
LEFT OUTER JOIN part_tag parts3_ ON tag2_.id=parts3_.tag_id
LEFT OUTER JOIN Part part4_ ON parts3_.part_id=part4_.id
WHERE (t.id IN
         (SELECT task5_.id
          FROM Task task5_
          INNER JOIN task_tag tags6_ ON task5_.id=tags6_.task_id
          INNER JOIN Tag tag7_ ON tags6_.tag_id=tag7_.id
          WHERE tag7_.name IN (${tags})
          GROUP BY task5_.id HAVING count(task5_.id)= ${tags_count} ))
ORDER BY tag2_.name ASC