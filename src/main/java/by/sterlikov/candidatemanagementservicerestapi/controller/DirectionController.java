package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.dto.DirectionDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.DirectionMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.Direction;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import by.sterlikov.candidatemanagementservicerestapi.service.DirectionService;
import by.sterlikov.candidatemanagementservicerestapi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/direction")
public class DirectionController {
    private final UserService userService;
    final DirectionService directionService;
    final DirectionMapper directionMapper;

    public DirectionController(UserService userService, DirectionService directionService, DirectionMapper directionMapper) {
        this.userService = userService;
        this.directionService = directionService;
        this.directionMapper = directionMapper;
    }

    @GetMapping("/{directionId}/users")
    public Page<User> getCandidatesByDirection(@PathVariable Long directionId,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(required = false) String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.getCandidatesByDirection(directionId, pageable);
    }

    @PostMapping("/add")
    public ResponseEntity<Direction> addDirection(@RequestBody DirectionDto dto) {
        Direction directionDtoToDirection = directionMapper.directionDtoToDirection(dto);
        Direction direction = directionService.create(directionDtoToDirection);
        return ResponseEntity.ok(direction);
    }

    @PostMapping("/update/user/{id}")
    public ResponseEntity<User> updateDirection(@PathVariable("id") Long id,
                                                @RequestPart("direction") DirectionDto dto) {
        Direction direction = directionMapper.directionDtoToDirection(dto);
        if (userService.findById(id).isPresent()) {
            User currentUser = userService.findById(id).get();
            currentUser.getDirections().add(direction);
            directionService.create(direction);
            User updateUser = userService.updateUser(currentUser);
            return ResponseEntity.ok(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
