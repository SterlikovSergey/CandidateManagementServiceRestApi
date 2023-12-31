package by.sterlikov.candidatemanagementservicerestapi.mapper;

import by.sterlikov.candidatemanagementservicerestapi.dto.AvatarDto;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AvatarMapper {
    public User avatarDtoToUser(AvatarDto dto) throws IOException {
        User user = new User();
        user.setAvatar(dto.getAvatar().getBytes());
        return user;
    }
}
