package by.sterlikov.candidatemanagementservicerestapi.mapper;

import by.sterlikov.candidatemanagementservicerestapi.dto.TestDto;
import by.sterlikov.candidatemanagementservicerestapi.model.Test;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {

    public Test createTestDtoToTest(TestDto testDto) {
        Test test = new Test();
        test.setName(testDto.getName());
        test.setDescription(testDto.getDescription());
        return test;
    }

}
