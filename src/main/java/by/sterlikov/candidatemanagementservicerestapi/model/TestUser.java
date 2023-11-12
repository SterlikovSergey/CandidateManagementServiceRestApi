package by.sterlikov.candidatemanagementservicerestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test_candidate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private User ownerTest;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    private LocalDate testedAt;
    private int grade;



}
