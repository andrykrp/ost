package org.octocode.services;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.octocode.domain.Task;
import org.octocode.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Service
@Path("task")
public class TaskService {
    @Autowired
    TaskRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Task getTask(@QueryParam("id") String id) throws JSONException {
        Task task = null;
        if (id != null && id.matches("\\d+")) {
            task = repository.findOne(Long.parseLong(id));
        }
        return task == null ? Task.BLANK : task;
    }
}
