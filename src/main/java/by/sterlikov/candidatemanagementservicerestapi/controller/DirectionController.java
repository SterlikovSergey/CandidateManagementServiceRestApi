package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.dto.DirectionDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.DirectionMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.Direction;
import by.sterlikov.candidatemanagementservicerestapi.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direction")
public class DirectionController {
    final DirectionService directionService;
    final DirectionMapper directionMapper;

    public DirectionController(DirectionService directionService, DirectionMapper directionMapper) {
        this.directionService = directionService;
        this.directionMapper = directionMapper;
    }

    @PostMapping
    public ResponseEntity<Direction> addDirection(@RequestBody DirectionDto dto){
        Direction directionDtoToDirection = directionMapper.directionDtoToDirection(dto);
        Direction direction = directionService.create(directionDtoToDirection);
        return ResponseEntity.ok(direction);
    }

}
