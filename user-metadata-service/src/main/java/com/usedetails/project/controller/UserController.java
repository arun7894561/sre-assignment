package com.usedetails.project.controller;

import com.usedetails.project.dto.UserRequest;
import com.usedetails.project.entity.User;
import com.usedetails.project.metrics.MetricsService;
import com.usedetails.project.service.UserService;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MetricsService metricsService;

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody UserRequest request) {

        Timer.Sample sample = metricsService.startTimer();
        metricsService.incrementTotalRequests();

        try {

            User user = userService.createUser(request);

            metricsService.incrementSuccess();
            return ResponseEntity.status(HttpStatus.CREATED).body(user);

        } catch (Exception e) {

            metricsService.incrementFailure();
            throw e;

        } finally {

            metricsService.stopTimer(sample);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){

        User user = userService.getUser(id);

        return ResponseEntity.ok(user);
    }
}