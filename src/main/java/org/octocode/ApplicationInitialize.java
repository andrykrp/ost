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
        for (int j = 1; j < 50; j++) {
            Tag tag = new Tag("tag-" + j);
            tagRepository.save(tag);
        }

        for (int i = 1; i < 15; i++) {
            int rating = i % 5 + 1;
            Task task = new Task("name" + i, "description" + i, rating);
            Long id = taskRepository.save(task).getId();

            for (int j = 1; j < 15; j++) {
                long tagId = j % i + j % rating + i % rating;
                Tag tag = tagRepository.findOne(tagId);
                task = taskRepository.findOne(id);
                if (tag != null) {
                    task.add(tag);
                    taskRepository.save(task);

                    tag.add(task);
                    tagRepository.save(tag);
                }
            }
        }

        for (int i = 1; i < 5; i++) {
            Part part = new Part("group-" + i);
            Long id = groupRepository.save(part).getId();

            for (int j = 1; j < 7; j++) {
                long tagId = j % i + i % j + j;
                Tag tag = tagRepository.findOne(tagId);
                part = groupRepository.findOne(id);
                if (tag != null) {
                    part.add(tag);
                    groupRepository.save(part);

                    tag.add(part);
                    tagRepository.save(tag);
                }
            }
        }
    }
}
