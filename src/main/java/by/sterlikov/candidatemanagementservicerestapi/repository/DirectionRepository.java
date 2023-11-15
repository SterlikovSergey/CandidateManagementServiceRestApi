package by.sterlikov.candidatemanagementservicerestapi.repository;

import by.sterlikov.candidatemanagementservicerestapi.model.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    @Override
    List<Direction> findAll();

    @Override
    List<Direction> findAll(Sort sort);
}
