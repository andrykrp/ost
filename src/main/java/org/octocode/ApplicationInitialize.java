package org.octocode;

import org.octocode.domain.Part;
import org.octocode.domain.Tag;
import org.octocode.domain.Task;
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
//        for (int j = 1; j < 50; j++) {
//            Tag tag = new Tag("tag-" + j);
//            tagRepository.save(tag);
//        }
//
////        Tag t = new Tag("777");
////        tagRepository.save(t);
//
//        for (int i = 1; i < 15; i++) {
//            int rating = i % 5 + 1;
//            Task task = new Task("name" + i, "description" + i, rating);
//            taskRepository.save(task);
//
//            for (int j = 1; j < 15; j++) {
//                long tagId = j % i + j % rating + i % rating;
//                Tag tag = tagRepository.findOne(tagId);
//                if (tag != null)
//                    task.add(tag);
//            }
//            taskRepository.save(task);
//        }
//
//        for (int i = 1; i < 5; i++) {
//            Part part = new Part("group-" + i);
//            groupRepository.save(part);
//
//            for (int j = 20; j < 25; j++) {
//                long tagId = j % i + i % j + j;
//                Tag tag = tagRepository.findOne(tagId);
//                if (tag != null)
//                    part.add(tag);
//            }
//            groupRepository.save(part);
//        }
    }
}
