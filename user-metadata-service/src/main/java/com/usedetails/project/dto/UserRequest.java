package com.usedetails.project.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String userId;
    private String name;
    private String email;
    private String phone;

}