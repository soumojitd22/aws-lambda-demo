package org.demo.aws.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.demo.aws.lambda.model.DemoRequest;
import org.demo.aws.lambda.model.DemoResponse;
import org.demo.aws.lambda.processor.AppRequestProcessor;

public class AppRequestHandler {

    private static final Logger LOGGER = LogManager.getLogger(AppRequestHandler.class);

    private AppRequestProcessor appRequestProcessor = new AppRequestProcessor();

    public DemoResponse handleRequest(DemoRequest request, Context context) {

        LOGGER.info("Request received : {}", request.toString());
        return appRequestProcessor.process(request);
    }
}
