package by.sterlikov.candidatemanagementservicerestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "result")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private int grade;

    @ManyToOne
    @JoinColumn(name = "candidate_test_id")
    private UserTest userTest;
}
