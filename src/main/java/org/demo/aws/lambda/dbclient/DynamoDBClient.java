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

import static org.demo.aws.lambda.constant.IConstant.*;

public class DynamoDBClient {

    private static final String TABLE_NAME = System.getenv(APP_TABLE_SYS_VAR_NAME);
    private static final Table APP_TABLE = initializeTable();

    private static Table initializeTable() {
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
        return dynamoDB.getTable(TABLE_NAME);
    }

    public static boolean insertData(DemoRequest demoRequest, final LambdaLogger logger) {
        try {
            APP_TABLE.putItem(new Item()
                    .withPrimaryKey(APP_TABLE_COL_1, demoRequest.getId())
                    .withString(APP_TABLE_COL_2, demoRequest.getName())
                    .withNumber(APP_TABLE_COL_3, demoRequest.getScore()));
        } catch (ResourceNotFoundException e) {
            logger.log("Error: The table " + TABLE_NAME + " can't be found");
            return false;
        } catch (AmazonServiceException e) {
            logger.log(e.getMessage());
            return false;
        }
        return true;
    }
}
