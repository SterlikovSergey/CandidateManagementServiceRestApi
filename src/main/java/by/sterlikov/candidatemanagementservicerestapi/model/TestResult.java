package by.sterlikov.candidatemanagementservicerestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "test_result")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate testedAt;
    private int grade;

    @ManyToOne
    @JoinColumn(name = "test_candidate_id", referencedColumnName = "id")
    private TestUser ownerResult;
}
