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
        json.put("key", repository.findOne(0l));
        json.put("value", "3");
        return json.toString();
    }
}
