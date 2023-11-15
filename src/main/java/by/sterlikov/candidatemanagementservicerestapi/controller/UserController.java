package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.configuration.JWTTokenProvider;
import by.sterlikov.candidatemanagementservicerestapi.dto.*;
import by.sterlikov.candidatemanagementservicerestapi.mapper.AvatarMapper;
import by.sterlikov.candidatemanagementservicerestapi.mapper.CvFileMapper;
import by.sterlikov.candidatemanagementservicerestapi.mapper.UserMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import by.sterlikov.candidatemanagementservicerestapi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AvatarMapper avatarMapper;
    private final JWTTokenProvider tokenProvider;
    private final CvFileMapper cvFileMapper;


    public UserController(UserService userService, UserMapper userMapper,
                          AvatarMapper avatarMapper, JWTTokenProvider tokenProvider, CvFileMapper cvFileMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.avatarMapper = avatarMapper;
        this.tokenProvider = tokenProvider;
        this.cvFileMapper = cvFileMapper;
    }

    @GetMapping("/all")
    public Page<User> getAllCandidates(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(required = false) String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.getAllCandidates(pageable);
    }

    @GetMapping("/filtered")
    public Page<User> getCandidatesByName(@RequestParam String name,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(required = false) String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.getCandidatesByFirstName(name, pageable);
    }

    @PostMapping
    public ResponseEntity<User> registration(@RequestBody UserDto dto) {
        User userDtoToUser = userMapper.createUserDtoToUser(dto);
        User user = userService.create(userDtoToUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{id}/avatar")
    public ResponseEntity<User> updateAvatar(@PathVariable("id") Long id,
                                             @RequestPart("avatar") MultipartFile avatar) throws IOException {
        AvatarDto avatarDto = new AvatarDto();
        avatarDto.setAvatar(avatar);
        User addAvatarDtoToUser = avatarMapper.avatarDtoToUser(avatarDto);
        if (getOptionalUser(id).isPresent()) {
            User currentUser = getOptionalUser(id).get();
            currentUser.setAvatar(addAvatarDtoToUser.getAvatar());
            User updateUser = userService.updateUser(currentUser);
            return ResponseEntity.ok(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/cv")
    public ResponseEntity<User> updateCvFile(@PathVariable("id") Long id,
                                             @RequestPart("cv") MultipartFile cvFile) throws IOException {
        CvFileDto cvFileDto = new CvFileDto();
        cvFileDto.setCvFile(cvFile);
        User addCvFileDtoToUser = cvFileMapper.cvFileDtoToUser(cvFileDto);
        if (getOptionalUser(id).isPresent()) {
            User currentUser = getOptionalUser(id).get();
            currentUser.setCvFile(addCvFileDtoToUser.getCvFile());
            User updateUser = userService.updateUser(currentUser);
            return ResponseEntity.ok(updateUser);
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

    private Optional<User> getOptionalUser(Long id) {
        return userService.findById(id);
    }
}

