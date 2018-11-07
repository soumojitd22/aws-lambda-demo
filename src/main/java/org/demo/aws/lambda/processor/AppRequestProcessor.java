package org.demo.aws.lambda.processor;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.demo.aws.lambda.dbclient.DynamoDBClient;
import org.demo.aws.lambda.model.DemoRequest;
import org.demo.aws.lambda.model.DemoResponse;

public class AppRequestProcessor {

    private DynamoDBClient dynamoDBClient = new DynamoDBClient();

    public DemoResponse process(DemoRequest demoRequest, final LambdaLogger logger) {
        DemoResponse demoResponse = new DemoResponse();
        if (dynamoDBClient.insertData(demoRequest, logger))
            demoResponse.setMessage("Hello " + demoRequest.getName() + "!!");
        else
            demoResponse.setMessage("Ahh! Error occurred..");
        
        return demoResponse;
    }
}
