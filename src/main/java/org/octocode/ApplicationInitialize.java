package org.octocode;

import org.octocode.domains.Part;
import org.octocode.domains.Tag;
import org.octocode.domains.Task;
import org.octocode.repositories.PartRepository;
import org.octocode.repositories.TagRepository;
import org.octocode.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationInitialize {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PartRepository groupRepository;

    public void init() {

        addGroups();
        addTasks();

//        for (int j = 1; j < 50; j++) {
//            Tag tag = new Tag("tag-" + j);
//            tagRepository.save(tag);
//        }

//        for (int i = 1; i < 15; i++) {
//            int rating = i % 5 + 1;
//            Task task = new Task("name" + i, "description" + i, rating);
//            Long id = taskRepository.save(task).getId();
//
//            for (int j = 1; j < 15; j++) {
//                long tagId = j % i + j % rating + i % rating;
//                Tag tag = tagRepository.findOne(tagId);
//                task = taskRepository.findOne(id);
//                if (tag != null) {
//                    task.add(tag);
//                    taskRepository.save(task);
//
//                    tag.add(task);
//                    tagRepository.save(tag);
//                }
//            }
//        }

//        for (int i = 1; i < 5; i++) {
//            Part part = new Part("group-" + i);
//            Long id = groupRepository.save(part).getId();
//
//            for (int j = 1; j < 7; j++) {
//                long tagId = j % i + i % j + j;
//                Tag tag = tagRepository.findOne(tagId);
//                part = groupRepository.findOne(id);
//                if (tag != null) {
//                    part.add(tag);
//                    groupRepository.save(part);
//
//                    tag.add(part);
//                    tagRepository.save(tag);
//                }
//            }
//        }
    }

    private void addTasks() {
        save(taskRepository.save(new Task("Задача - 1", "", 3)).getId(), "Разработка", "Не начата", "Версия 2");
        save(taskRepository.save(new Task("Задача - 2", "", 2)).getId(), "Разработка", "В работе", "Версия 2");
        save(taskRepository.save(new Task("Задача - 3", "", 4)).getId(), "Тестирование", "В работе", "Версия 1");
        save(taskRepository.save(new Task("Задача - 4", "", 5)).getId(), "Тестирование", "Завершена", "Версия 2");
    }

    private void save(Long id, String t1, String t2, String t3) {
        Task task = taskRepository.findOne(id);
        Tag tag = tagRepository.findByName(t1);
        save(task, tag);
        tag = tagRepository.findByName(t2);
        save(task, tag);
        tag = tagRepository.findByName(t3);
        save(task, tag);
    }

    private void save(Task task, Tag tag) {
        task.add(tag);
        taskRepository.save(task);
        tag.add(task);
        tagRepository.save(tag);
    }

    private void addGroups() {
        Part p = groupRepository.save(new Part("Стадия"));
        save(p, tagRepository.save(new Tag("Разработка")));
        save(p, tagRepository.save(new Tag("Тестирование")));
        save(p, tagRepository.save(new Tag("Аналитика")));

        p = groupRepository.save(new Part("Процесс"));
        save(p, tagRepository.save(new Tag("Не начата")));
        save(p, tagRepository.save(new Tag("В работе")));
        save(p, tagRepository.save(new Tag("Завершена")));

        p = groupRepository.save(new Part("Версия"));
        save(p, tagRepository.save(new Tag("Версия 1")));
        save(p, tagRepository.save(new Tag("Версия 2")));
        save(p, tagRepository.save(new Tag("Версия 3")));
    }

    private void save(Part part, Tag tag) {
        part.add(tag);
        groupRepository.save(part);

        tag.add(part);
        tagRepository.save(tag);
    }
}
