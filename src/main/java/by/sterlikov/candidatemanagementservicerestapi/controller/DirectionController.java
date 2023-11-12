package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.dto.CreateDirectionDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.DirectionMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.Direction;
import by.sterlikov.candidatemanagementservicerestapi.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direction")
public class DirectionController {
    @Autowired
    DirectionService directionService;
    @Autowired
    DirectionMapper directionMapper;

    @PostMapping
    public ResponseEntity<Direction> addDirection(CreateDirectionDto dto){
        Direction directionDtoToDirection = directionMapper.createDirectionDtoToDirection(dto);
        Direction direction = directionService.create(directionDtoToDirection);
        return ResponseEntity.ok(direction);
    }

}
