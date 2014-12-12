SELECT task0_.id AS id1_3_,
       task0_.author AS author2_3_,
       task0_.description AS descript3_3_,
       task0_.name AS name4_3_,
       task0_.rating AS rating5_3_
FROM Task task0_
INNER JOIN task_tag tags1_ ON task0_.id=tags1_.task_id
INNER JOIN Tag tag2_ ON tags1_.tag_id=tag2_.id
LEFT OUTER JOIN part_tag parts3_ ON tag2_.id=parts3_.tag_id
LEFT OUTER JOIN Part part4_ ON parts3_.part_id=part4_.id
WHERE (task0_.id IN
         (SELECT task5_.id
          FROM Task task5_
          INNER JOIN task_tag tags6_ ON task5_.id=tags6_.task_id
          INNER JOIN Tag tag7_ ON tags6_.tag_id=tag7_.id
          WHERE tag7_.name IN (?)
          GROUP BY task5_.id HAVING count(task5_.id)=2))