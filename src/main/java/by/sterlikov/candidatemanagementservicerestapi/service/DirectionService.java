package by.sterlikov.candidatemanagementservicerestapi.service;

import by.sterlikov.candidatemanagementservicerestapi.model.Direction;
import by.sterlikov.candidatemanagementservicerestapi.repository.DirectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DirectionService {
    public final DirectionRepository directionRepository;

    public Direction create(Direction direction){
        return directionRepository.save(direction);
    }

    public Boolean existsDirection(Direction direction){
        Optional<Direction> byDirectionName = directionRepository.findDirectionByName(direction.getName());
        return byDirectionName.isPresent();
    }


}
