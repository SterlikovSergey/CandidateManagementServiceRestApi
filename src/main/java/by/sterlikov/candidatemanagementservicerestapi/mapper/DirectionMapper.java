package by.sterlikov.candidatemanagementservicerestapi.mapper;

import by.sterlikov.candidatemanagementservicerestapi.dto.DirectionDto;
import by.sterlikov.candidatemanagementservicerestapi.model.Direction;
import org.springframework.stereotype.Component;

@Component
public class DirectionMapper {
    public Direction directionDtoToDirection(DirectionDto dto) {
        Direction direction = new Direction();
        direction.setName(dto.getName());
        direction.setDescription(dto.getDescription());
        return direction;
    }
}
