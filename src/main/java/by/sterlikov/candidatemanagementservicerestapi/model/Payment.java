package by.sterlikov.candidatemanagementservicerestapi.model;

import by.sterlikov.candidatemanagementservicerestapi.model.enums.PaymentStatus;
import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private PaymentStatus status;

    @OneToOne
    private Order order;

}
