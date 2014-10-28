package org.octocode.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;
import org.octocode.domain.Task;
import org.octocode.repositories.TaskRepository;
import org.octocode.services.exception.ProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
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
    public Task save(Task task) throws JSONException {
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
    public List<Task> getTasks(@QueryParam("tag") List<String> tags, @QueryParam("order") List<String> orders) throws JSONException {
        List<Task> list = repository.findByTags(tags);
        return list == null ? new ArrayList<Task>() : list;
    }

    @javax.ws.rs.ext.Provider
    @javax.ws.rs.Consumes("application/json")
    @javax.ws.rs.Produces("application/json")
    public static class TaskMessageBodyReader implements MessageBodyReader<Task> {

        @Override
        public boolean isReadable(Class<?> type, Type genericType,
                                  Annotation[] annotations, MediaType mediaType) {
            return type == Task.class;
        }

        @Override
        public Task readFrom(Class<Task> type,
                             Type genericType,
                             Annotation[] annotations, MediaType mediaType,
                             MultivaluedMap<String, String> httpHeaders,
                             InputStream entityStream)
                throws IOException, WebApplicationException {

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(entityStream, Task.class);
        }
    }
}
