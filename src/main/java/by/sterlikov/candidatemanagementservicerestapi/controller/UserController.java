package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.configuration.JWTTokenProvider;
import by.sterlikov.candidatemanagementservicerestapi.dto.AvatarDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.AuthRequestDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.CvFileDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.UserDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.AvatarMapper;
import by.sterlikov.candidatemanagementservicerestapi.mapper.CvFileMapper;
import by.sterlikov.candidatemanagementservicerestapi.mapper.UserMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import by.sterlikov.candidatemanagementservicerestapi.service.UserService;
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

    private final UserService userService;
    private final UserMapper userMapper;
    private final AvatarMapper avatarMapper;
    private final JWTTokenProvider tokenProvider;
    private final CvFileMapper cvFileMapper;

    public UserController(UserService userService,
                          UserMapper userMapper,
                          AvatarMapper avatarMapper,
                          JWTTokenProvider tokenProvider,
                          CvFileMapper cvFileMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.avatarMapper = avatarMapper;
        this.tokenProvider = tokenProvider;
        this.cvFileMapper = cvFileMapper;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> registration(@RequestBody UserDto dto) {
        User userDtoToUser = userMapper.createUserDtoToUser(dto);
        User user = userService.create(userDtoToUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/avatar")
    public ResponseEntity<User> updateAvatar(@RequestPart("user") User user, @RequestPart("avatar")
    MultipartFile avatar) throws IOException {
        AvatarDto avatarDto = new AvatarDto();
        avatarDto.setAvatar(avatar);
        User addAvatarDtoToUser = avatarMapper.avatarDtoToUser(avatarDto);
        if (getOptionalUser(user).isPresent()) {
            User currentUser = getOptionalUser(user).get();
            currentUser.setAvatar(addAvatarDtoToUser.getAvatar());
            User updateUser = userService.updateUser(currentUser);
            return ResponseEntity.ok(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cv")
    public ResponseEntity<User> updateCvFile(@RequestPart("user") User user, @RequestPart("cv")
    MultipartFile cvFile) throws IOException {
        CvFileDto cvFileDto = new CvFileDto();
        cvFileDto.setCvFile(cvFile);
        User addCvFileDtoToUser = cvFileMapper.cvFileDtoToUser(cvFileDto);
        if (getOptionalUser(user).isPresent()) {
            User currentUser = getOptionalUser(user).get();
            currentUser.setCvFile(addCvFileDtoToUser.getCvFile());
            User updateUser = userService.updateUser(currentUser);
            return ResponseEntity.ok(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Optional<User> getOptionalUser(User user) {
        return userService.findById(user.getId());
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

