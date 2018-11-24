package org.demo.aws.lambda.dbclient;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.demo.aws.lambda.model.DemoRequest;

import static org.demo.aws.lambda.constant.IConstant.*;

public class DynamoDBClient {

    private static final Logger LOGGER = LogManager.getLogger(DynamoDBClient.class);
    private static final String TABLE_NAME = System.getenv(APP_TABLE_SYS_VAR_NAME);
    private static final Table APP_TABLE = initializeTable();

    private static Table initializeTable() {
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
        return dynamoDB.getTable(TABLE_NAME);
    }

    public boolean insertData(DemoRequest demoRequest) {
        try {
            APP_TABLE.putItem(new Item()
                    .withPrimaryKey(APP_TABLE_COL_1, demoRequest.getId())
                    .withString(APP_TABLE_COL_2, demoRequest.getName())
                    .withNumber(APP_TABLE_COL_3, demoRequest.getScore()));
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Error: The table {} can't be found", TABLE_NAME);
            return false;
        } catch (AmazonServiceException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }
}
