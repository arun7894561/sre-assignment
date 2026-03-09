package com.usedetails.project.service;

import com.usedetails.project.dto.UserRequest;
import com.usedetails.project.entity.User;
import com.usedetails.project.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Retry(name="dbRetry")
    @CircuitBreaker(name="dbCircuitBreaker", fallbackMethod="fallbackCreate")
    public User createUser(UserRequest request) {

        Optional<User> existingUser =
                userRepository.findById(request.getUserId());

        if(existingUser.isPresent()){
            return existingUser.get();
        }

        User user = new User(
                request.getUserId(),
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                LocalDateTime.now()
        );

        return userRepository.save(user);
    }

    @CircuitBreaker(name="dbCircuitBreaker", fallbackMethod="fallbackGet")
    public User getUser(String id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public User fallbackCreate(UserRequest request, Throwable ex) {
        throw new RuntimeException("Database unavailable");
    }

    public User fallbackGet(String id, Throwable ex) {
        throw new RuntimeException("Database unavailable");
    }
}