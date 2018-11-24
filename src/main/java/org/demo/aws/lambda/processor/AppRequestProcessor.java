package org.demo.aws.lambda.processor;

import org.demo.aws.lambda.dbclient.DynamoDBClient;
import org.demo.aws.lambda.model.DemoRequest;
import org.demo.aws.lambda.model.DemoResponse;

public class AppRequestProcessor {

    private DynamoDBClient dynamoDBClient = new DynamoDBClient();

    public DemoResponse process(DemoRequest demoRequest) {
        DemoResponse demoResponse = new DemoResponse();
        if (dynamoDBClient.insertData(demoRequest))
            demoResponse.setMessage("Hello " + demoRequest.getName() + "!!");
        else
            demoResponse.setMessage("Ahh! Error occurred..");

        return demoResponse;
    }
}
