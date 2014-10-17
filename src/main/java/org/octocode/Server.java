package org.octocode;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.octocode.config.ApplicationConfig;
import org.octocode.repositories.TaskRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URI;

public class Server {
    public static final String BASE_URI = "http://localhost:8080/ost/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("org.octocode");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = startServer();
        AnnotationConfigApplicationContext  ctx = new AnnotationConfigApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.refresh();

        TaskRepository r = ctx.getBean(TaskRepository.class);

        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

