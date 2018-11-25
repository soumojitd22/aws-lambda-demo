package org.demo.aws.lambda.db.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import static org.demo.aws.lambda.constant.IConstant.*;

@DynamoDBTable(tableName = APP_TABLE_NAME)
public class DemoAppEntity {
    private int id;
    private String name;
    private int score;

    @DynamoDBHashKey(attributeName = APP_TABLE_COL_1)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = APP_TABLE_COL_2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = APP_TABLE_COL_3)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
