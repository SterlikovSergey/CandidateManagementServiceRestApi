package by.sterlikov.candidatemanagementservicerestapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class CvFileDto {
    private MultipartFile CvFile;
}
