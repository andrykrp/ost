package org.octocode;

import org.octocode.domain.Tag;
import org.octocode.domain.Task;
import org.octocode.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationInitialize {
    @Autowired
    TaskRepository repository;

    public void init() {
        for (int i = 1; i < 15; i++) {
            int rating = i % 5 + 1;
            Task task = new Task("name" + i, "description" + i, rating);
            for (int j = 1; j < 15; j++) {
                task.add(new Tag("" + (j % i + j % rating + i % rating)));
            }
            repository.save(task);
        }
        new Tag("2").equals(new Tag("2"));
    }
}
