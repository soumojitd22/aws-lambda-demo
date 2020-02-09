package org.demo.aws.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.apache.logging.log4j.Logger;
import org.demo.aws.lambda.model.DemoRequest;
import org.demo.aws.lambda.model.DemoResponse;
import org.demo.aws.lambda.processor.AppRequestProcessor;

import javax.inject.Inject;
import javax.inject.Named;

import static org.apache.logging.log4j.CloseableThreadContext.put;
import static org.apache.logging.log4j.LogManager.getLogger;

@Named("appRequestHandler")
public class AppRequestHandler implements RequestHandler<DemoRequest, DemoResponse> {
    private static final Logger LOGGER = getLogger(AppRequestHandler.class);

    @Inject
    AppRequestProcessor appRequestProcessor;

    @Override
    public DemoResponse handleRequest(DemoRequest request, Context context) {
        try (final Instance ignore = put("requestId", context.getAwsRequestId())) {
            LOGGER.info("Request received : {}", request);
            return appRequestProcessor.process(request);
        }
    }
}
