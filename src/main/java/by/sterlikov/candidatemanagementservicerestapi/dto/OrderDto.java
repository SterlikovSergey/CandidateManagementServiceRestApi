package by.sterlikov.candidatemanagementservicerestapi.dto;

import by.sterlikov.candidatemanagementservicerestapi.model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class OrderDto {
    private Product product;
}
