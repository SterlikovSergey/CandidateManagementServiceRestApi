package by.sterlikov.candidatemanagementservicerestapi.controller;

import by.sterlikov.candidatemanagementservicerestapi.configuration.JWTTokenProvider;
import by.sterlikov.candidatemanagementservicerestapi.dto.AuthRequestDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.AvatarDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.CvFileDto;
import by.sterlikov.candidatemanagementservicerestapi.dto.UserDto;
import by.sterlikov.candidatemanagementservicerestapi.mapper.AvatarMapper;
import by.sterlikov.candidatemanagementservicerestapi.mapper.CvFileMapper;
import by.sterlikov.candidatemanagementservicerestapi.mapper.UserMapper;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import by.sterlikov.candidatemanagementservicerestapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@Tag(name = "User resource", description = "description from user resource")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AvatarMapper avatarMapper;
    private final JWTTokenProvider tokenProvider;
    private final CvFileMapper cvFileMapper;

    @GetMapping("/all")
    public Page<User> getAllCandidates(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(required = false) String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userService.getAllCandidates(pageable);
    }

    @GetMapping("/{name}")
    @Operation(summary = "find by name", description = "Find User by name")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<User> getCandidateByName(@PathVariable String name) {
        User byName = userService.getCandidateByName(name);
        return ResponseEntity.ok(byName);
    }


    @GetMapping("/getAllByUsername/{username}/{page}")
    @Operation(summary = "create list users", description = "all users by username")
    public ResponseEntity<List<User>> getAllCandidatesByUsername(@PathVariable String username,
                                                                 @PathVariable Integer page,
                                                                 @RequestParam Integer size) {
        List<User> candidatesByName = userService.getCandidatesByName(username, PageRequest.of(page, size));
        return ResponseEntity.ok(candidatesByName);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registration(@RequestBody UserDto dto) {
        User userDtoToUser = userMapper.createUserDtoToUser(dto);
        User user = userService.create(userDtoToUser);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/avatar")
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

