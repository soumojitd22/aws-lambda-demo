package org.demo.aws.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.demo.aws.lambda.model.DemoRequest;
import org.demo.aws.lambda.model.DemoResponse;
import org.demo.aws.lambda.processor.AppRequestProcessor;

public class AppRequestHandler implements RequestHandler<DemoRequest, DemoResponse> {

    @Override
    public DemoResponse handleRequest(DemoRequest request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Request received : " + request.toString());
        return AppRequestProcessor.process(request, logger);
    }
}
