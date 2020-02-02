package org.demo.aws.lambda.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.demo.aws.lambda.db.client.DynamoDBClient;
import org.demo.aws.lambda.db.entity.DemoAppEntity;
import org.demo.aws.lambda.mapper.ObjectMapper;
import org.demo.aws.lambda.model.DemoRequest;
import org.demo.aws.lambda.model.DemoResponse;

import static org.demo.aws.lambda.constant.IConstant.FAILURE_MESSAGE;

public class AppRequestProcessor {

    private static final Logger LOGGER = LogManager.getLogger(AppRequestProcessor.class);

    private DynamoDBClient dynamoDBClient = new DynamoDBClient();

    public DemoResponse process(DemoRequest demoRequest) {
        LOGGER.info("Request received");
        String responseMessage;
        DemoAppEntity appEntity = ObjectMapper.INSTANCE.convertDtoToEntity(demoRequest);
        try {
            dynamoDBClient.insertData(appEntity);
            responseMessage = "Hello " + demoRequest.getName() + "!!";
        } catch (Exception ex) {
            responseMessage = FAILURE_MESSAGE;
            LOGGER.error("Error occurred :: ", ex);
        }
        return ObjectMapper.INSTANCE.generateResponse(responseMessage);
    }
}
