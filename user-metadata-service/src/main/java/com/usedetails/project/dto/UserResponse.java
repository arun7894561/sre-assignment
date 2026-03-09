package com.usedetails.project.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private String userId;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;

}