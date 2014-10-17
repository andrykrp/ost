package org.octocode.services;

import org.octocode.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("task")
public class TaskService {
    @Autowired
    TaskRepository repository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        repository.findOne(0l);
        return "Got it!";
    }
}
