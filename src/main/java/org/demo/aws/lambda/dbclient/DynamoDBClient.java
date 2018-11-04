package org.demo.aws.lambda.dbclient;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.demo.aws.lambda.model.DemoRequest;

import java.util.HashMap;
import java.util.Map;

public class DynamoDBClient {

    private static final String TABLE_NAME = "DemoAppData";
    private static final AmazonDynamoDB DYNAMO_DB = AmazonDynamoDBClientBuilder.defaultClient();

    public static boolean insertData(DemoRequest demoRequest, final LambdaLogger logger) {
        boolean isDataInserted;
        try {
            DYNAMO_DB.putItem(TABLE_NAME, mapData(demoRequest));
            isDataInserted = true;
        } catch (ResourceNotFoundException e) {
            logger.log("Error: The table " + TABLE_NAME + " can't be found");
            isDataInserted = false;
        } catch (AmazonServiceException e) {
            logger.log(e.getMessage());
            isDataInserted = false;
        }
        return isDataInserted;
    }

    private static Map<String, AttributeValue> mapData(DemoRequest demoRequest) {
        Map<String, AttributeValue> mapItem = new HashMap<>();
        mapItem.put("Id", new AttributeValue(String.valueOf(demoRequest.getId())));
        mapItem.put("Name", new AttributeValue(demoRequest.getName()));
        mapItem.put("Score", new AttributeValue(String.valueOf(demoRequest.getScore())));
        return mapItem;
    }
}
