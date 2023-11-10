package by.sterlikov.candidatemanagementservicerestapi.mapper;

import by.sterlikov.candidatemanagementservicerestapi.dto.CreateUserDto;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User createUserDtoToUser(CreateUserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
