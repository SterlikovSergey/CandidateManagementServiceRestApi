package by.sterlikov.candidatemanagementservicerestapi.mapper;

import by.sterlikov.candidatemanagementservicerestapi.dto.CvFileDto;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CvFileMapper {
    public User cvFileDtoToUser(CvFileDto dto) throws IOException {
        User user = new User();
        user.setCvFile(dto.getCvFile().getBytes());
        return user;
    }
}
