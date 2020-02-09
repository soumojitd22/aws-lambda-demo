package org.demo.aws.lambda.db.client;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.apache.logging.log4j.Logger;
import org.demo.aws.lambda.db.entity.DemoAppEntity;

import javax.inject.Singleton;

import static org.apache.logging.log4j.LogManager.getLogger;

@Singleton
public class DynamoDBClient {
    private static final Logger LOGGER = getLogger(DynamoDBClient.class);
    private static AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();

    private DynamoDBMapper dbMapper = new DynamoDBMapper(dynamoDBClient);

    public void insertData(DemoAppEntity appEntity) {
        dbMapper.save(appEntity);
        LOGGER.info("Record - {} saved into database", appEntity.getId());
    }
}
