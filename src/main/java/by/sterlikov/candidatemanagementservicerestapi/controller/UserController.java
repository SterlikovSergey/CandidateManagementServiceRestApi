package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.configuration.JWTTokenProvider;
import by.sterlikov.candidatemanagementservicerestapi.dto.AuthRequestDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.CreateUserDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.UserMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import by.sterlikov.candidatemanagementservicerestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<User> registration(@RequestBody CreateUserDto dto) {
        User userDtoToUser = userMapper.createUserDtoToUser(dto);
        User user = userService.create(userDtoToUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDto dto) {

        UserDetails userDetails = userService.loadUserByUsername(dto.getUsername());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(dto.getPassword(), userDetails.getPassword())) {
            String token = tokenProvider.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }
}

