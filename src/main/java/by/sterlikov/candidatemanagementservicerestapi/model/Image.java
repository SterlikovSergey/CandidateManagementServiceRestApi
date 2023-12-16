package by.sterlikov.candidatemanagementservicerestapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] imageBytes;

    private String name;

    private Long userId;

    private Long productId;

    private Long courierId;
}
