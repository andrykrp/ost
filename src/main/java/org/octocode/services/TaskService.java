package org.octocode.services;

import org.codehaus.jettison.json.JSONException;
import org.octocode.domain.Task;
import org.octocode.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    @Path("save")
    public Task save(@RequestBody Task task) throws JSONException {
        return repository.save(task);
    }

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
    public List<Task> getTasks(@QueryParam("tag") List<String> tags, @QueryParam("orderGroups") List<String> orderGroups, @QueryParam("orderFields")  List<String> orderFields) throws JSONException {
        orderGroups.add("group-1");
        orderGroups.add("group-3");
        List<Task> list = repository.findByTags(tags, orderGroups, orderFields);
        return list == null ? new ArrayList<Task>() : list;
    }

//    @javax.ws.rs.ext.Provider
//    @javax.ws.rs.Consumes("application/json")
//    @javax.ws.rs.Produces("application/json")
//    public static class TaskMessageBodyReader implements MessageBodyReader<Task> {
//
//        @Override
//        public boolean isReadable(Class<?> type, Type genericType,
//                                  Annotation[] annotations, MediaType mediaType) {
//            return type == Task.class;
//        }
//
//        @Override
//        public Task readFrom(Class<Task> type,
//                             Type genericType,
//                             Annotation[] annotations, MediaType mediaType,
//                             MultivaluedMap<String, String> httpHeaders,
//                             InputStream entityStream)
//                throws IOException, WebApplicationException {
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readValue(entityStream, Task.class);
//        }
//    }
}
