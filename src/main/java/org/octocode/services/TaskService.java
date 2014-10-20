package org.octocode.services;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.octocode.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service
@Path("task")
public class TaskService {
    @Autowired
    TaskRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIt() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("t1", repository.findOne(4l).getJSON());
        json.put("t2", repository.findOne(7l).getJSON());
        json.put("t3", repository.findOne(9l).getJSON());
        json.put("value", "3");
        return json.toString();
    }
}
