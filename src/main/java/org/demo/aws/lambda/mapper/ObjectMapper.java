package org.demo.aws.lambda.mapper;

import org.demo.aws.lambda.db.entity.DemoAppEntity;
import org.demo.aws.lambda.model.DemoRequest;
import org.demo.aws.lambda.model.DemoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ObjectMapper {
    ObjectMapper INSTANCE = Mappers.getMapper(ObjectMapper.class);

    DemoAppEntity convertDtoToEntity(DemoRequest demoRequest);

    DemoResponse generateResponse(String message);
}
