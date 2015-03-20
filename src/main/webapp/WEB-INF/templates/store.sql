SELECT task0_.*, data0_.name as 'Ver', data1_.name as 'Std', data2_.name as 'Prc'
FROM Task task0_
INNER JOIN
(SELECT tags1_.task_id, tag2_.name FROM task_tag tags1_
INNER JOIN Tag tag2_ ON tags1_.tag_id=tag2_.id 
INNER JOIN part_tag parts3_ ON tag2_.id=parts3_.tag_id
INNER JOIN Part part4_ ON parts3_.part_id=part4_.id AND part4_.name = 'Версия'
) as data0_ ON task0_.id=data0_.task_id
INNER JOIN
(SELECT tags1_.task_id, tag2_.name FROM task_tag tags1_
INNER JOIN Tag tag2_ ON tags1_.tag_id=tag2_.id 
INNER JOIN part_tag parts3_ ON tag2_.id=parts3_.tag_id
INNER JOIN Part part4_ ON parts3_.part_id=part4_.id AND part4_.name = 'Стадия'
) as data1_ ON task0_.id=data1_.task_id
INNER JOIN
(SELECT tags1_.task_id, tag2_.name FROM task_tag tags1_
INNER JOIN Tag tag2_ ON tags1_.tag_id=tag2_.id 
INNER JOIN part_tag parts3_ ON tag2_.id=parts3_.tag_id
INNER JOIN Part part4_ ON parts3_.part_id=part4_.id AND part4_.name = 'Процесс'
) as data2_ ON task0_.id=data2_.task_id
WHERE (task0_.id IN
         (SELECT task5_.id
          FROM Task task5_
          INNER JOIN task_tag tags6_ ON task5_.id=tags6_.task_id
          INNER JOIN Tag tag7_ ON tags6_.tag_id=tag7_.id
          WHERE tag7_.name IN ('В работе','Разработка')
          GROUP BY task5_.id HAVING count(task5_.id)=2))