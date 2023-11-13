package by.sterlikov.candidatemanagementservicerestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "direction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "directions")
    private Set<Test> tests = new HashSet<>();

    @ManyToMany(mappedBy = "directions")
    private Set<User> candidates = new HashSet<>();

}
