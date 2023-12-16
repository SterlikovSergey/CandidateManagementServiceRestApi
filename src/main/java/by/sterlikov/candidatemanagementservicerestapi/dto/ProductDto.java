package by.sterlikov.candidatemanagementservicerestapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductDto {
    private String name;
    private Short amount;
    private BigDecimal price;
    private Long categoryId;
}
