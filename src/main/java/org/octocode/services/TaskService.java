package org.octocode.services;

import org.codehaus.jettison.json.JSONException;
import org.octocode.domain.Tag;
import org.octocode.domain.Task;
import org.octocode.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Service
@Path("task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskService {
    @Autowired
    TaskRepository repository;

    @POST
    public Task getTask(@QueryParam("id") String id) throws JSONException {
        Task task = null;
        if (id != null && id.matches("\\d+")) {
            task = repository.findOne(Long.parseLong(id));
        }
        return task == null ? Task.BLANK : task;
    }

    @POST
    @Path("filter")
    public List<Task> getTasks(@QueryParam("tag") List<String> tags, @QueryParam("order") List<String> orders) throws JSONException {
        List<Task> list = repository.findByTagsNameIn(tags);
        return list == null ? new ArrayList<Task>() : list;
    }
}
