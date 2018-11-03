package org.demo.aws.lambda.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.demo.aws.lambda.model.DemoRequest;
import org.demo.aws.lambda.model.DemoResponse;

public class AppRequestHandler implements RequestHandler<DemoRequest, DemoResponse> {

    @Override
    public DemoResponse handleRequest(DemoRequest request, Context context) {
        context.getLogger().log(request.toString());
        DemoResponse demoResponse = new DemoResponse();
        demoResponse.setMessage("Welcome " + request.getName() + "!!");
        return demoResponse;
    }
}
