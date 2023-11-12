package by.sterlikov.candidatemanagementservicerestapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class AddAvatarDto {
    private String username;
    private MultipartFile avatar;
}
