package org.demo.aws.lambda.dbclient;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.demo.aws.lambda.model.DemoRequest;

public class DynamoDBClient {

    private static final String TABLE_NAME = System.getenv("APP_TABLE_NAME");
    private static final Table APP_TABLE = initializeTable();

    private static Table initializeTable() {
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
        return dynamoDB.getTable(TABLE_NAME);
    }

    public static boolean insertData(DemoRequest demoRequest, final LambdaLogger logger) {
        boolean isDataInserted;
        try {
            APP_TABLE.putItem(new Item()
                    .withPrimaryKey("Id", demoRequest.getId())
                    .withString("Name", demoRequest.getName())
                    .withNumber("Score", demoRequest.getScore()));
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
}
