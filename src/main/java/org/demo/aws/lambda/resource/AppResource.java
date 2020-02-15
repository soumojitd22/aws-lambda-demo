package org.demo.aws.lambda.resource;

import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.Logger;
import org.demo.aws.lambda.model.DemoRequest;
import org.demo.aws.lambda.model.DemoResponse;
import org.demo.aws.lambda.processor.AppRequestProcessor;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.apache.logging.log4j.CloseableThreadContext.put;
import static org.apache.logging.log4j.LogManager.getLogger;

@Path("/")
public class AppResource {
    private static final Logger LOGGER = getLogger(AppResource.class);

    @Inject
    AppRequestProcessor appRequestProcessor;

    @GET
    @Produces(TEXT_PLAIN)
    public String hello() {
        return "Hello!";
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public DemoResponse handleRequest(final DemoRequest request) {
        try (final CloseableThreadContext.Instance ignore = put("id", String.valueOf(request.getId()))) {
            LOGGER.info("Request received : {}", request);
            return appRequestProcessor.process(request);
        }
    }
}
