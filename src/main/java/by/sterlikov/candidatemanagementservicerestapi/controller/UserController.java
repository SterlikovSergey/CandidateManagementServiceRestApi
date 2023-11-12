package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.configuration.JWTTokenProvider;
import by.sterlikov.candidatemanagementservicerestapi.dto.AddAvatarDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.AuthRequestDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.CreateUserDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.AvatarMapper;
import by.sterlikov.candidatemanagementservicerestapi.mapper.UserMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import by.sterlikov.candidatemanagementservicerestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AvatarMapper avatarMapper;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @GetMapping("/db/allUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> registration(@RequestBody CreateUserDto dto) {
        User userDtoToUser = userMapper.createUserDtoToUser(dto);
        User user = userService.create(userDtoToUser);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/avatar")
    public ResponseEntity<String> updateAvatar(@PathVariable Long id,
                                               @RequestBody MultipartFile avatar) throws IOException {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setAvatar(avatar.getBytes());
            userService.updateUser(user);
            return ResponseEntity.ok("Avatar updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
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

