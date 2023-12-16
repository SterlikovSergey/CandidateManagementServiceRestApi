package by.sterlikov.candidatemanagementservicerestapi.service;

import by.sterlikov.candidatemanagementservicerestapi.exception.UserNotFoundException;
import by.sterlikov.candidatemanagementservicerestapi.model.Role;
import by.sterlikov.candidatemanagementservicerestapi.model.Test;
import by.sterlikov.candidatemanagementservicerestapi.model.User;
import by.sterlikov.candidatemanagementservicerestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User create(User user) {
        user.setRoles(Set.of(Role.USER));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.orElseThrow(UserNotFoundException::new);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }


    public Page<User> getAllCandidates(Pageable pageable) {
        return userRepository.findAllBy(pageable);
    }

    public User getCandidateByName(String name) {
        Optional<User> byName = userRepository.findByName(name);
        return byName.orElseThrow(UserNotFoundException::new);
    }

    public List<User> getCandidatesByName(String username, Pageable pageable) {
        return userRepository.findByName(username, pageable);
    }
    public Page<User> getCandidatesByDirection(Long directionId, Pageable pageable) {
        return userRepository.findByDirections_Id(directionId, pageable);
    }

}
