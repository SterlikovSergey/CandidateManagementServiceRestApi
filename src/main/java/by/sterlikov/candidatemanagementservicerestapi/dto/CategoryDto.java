package by.sterlikov.candidatemanagementservicerestapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class CategoryDto {

    @NotEmpty(message = "Please enter name categories")
    @NotBlank(message = "Please enter name categories")
    private String name;

}
