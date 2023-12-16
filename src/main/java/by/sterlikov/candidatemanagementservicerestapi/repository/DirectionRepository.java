package by.sterlikov.candidatemanagementservicerestapi.repository;

import by.sterlikov.candidatemanagementservicerestapi.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {

    Direction findByName(String name);

    Optional<Direction> findDirectionByName(String name);
}
