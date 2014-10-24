package org.octocode;

import org.octocode.domain.Tag;
import org.octocode.domain.Task;
import org.octocode.repositories.TagRepository;
import org.octocode.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationInitialize {
    @Autowired
    TaskRepository repository;

    @Autowired
    TagRepository tagRepository;

    public void init() {
        for (int j = 1; j < 50; j++) {
            Tag tag = new Tag("tag-" + j);
            tagRepository.save(tag);
        }

        for (int i = 1; i < 15; i++) {
            int rating = i % 5 + 1;
            Task task = new Task("name" + i, "description" + i, rating);
            repository.save(task);

            for (int j = 1; j < 15; j++) {
                long tagId = j % i + j % rating + i % rating;
                Tag tag = tagRepository.findOne(tagId);
                if (tag != null)
                    task.add(tag);
            }
            repository.save(task);
        }
    }
}
