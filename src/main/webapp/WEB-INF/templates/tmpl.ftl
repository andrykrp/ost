SELECT * FROM ( SELECT task0_.*<#list groups as grp> ,data${grp_index}_.name as '${grp}' </#list> FROM Task task0_
<#list groups as grp>
INNER JOIN (SELECT tt.task_id, t.name FROM task_tag tt INNER JOIN Tag t ON tt.tag_id=t.id INNER JOIN part_tag pt ON t.id=pt.tag_id INNER JOIN Part p ON pt.part_id=p.id AND p.name = '${grp}') as data${grp_index}_ ON task0_.id=data${grp_index}_.task_id
</#list>
WHERE (task0_.id IN (SELECT t.id FROM Task t INNER JOIN task_tag tt ON t.id=tt.task_id INNER JOIN Tag tg ON tt.tag_id=tg.id WHERE tg.name IN (<#list tags as tag>'${tag}'<#if tag_has_next>, </#if></#list>) GROUP BY t.id HAVING count(t.id)=${tags?size}))
) as data0_ <#if fields?size &gt; 0>ORDER BY <#list fields as fld>`${fld}` desc<#if fld_has_next>, </#if></#list></#if>