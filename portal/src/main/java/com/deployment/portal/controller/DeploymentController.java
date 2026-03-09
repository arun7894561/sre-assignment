package com.deployment.portal.controller;


import com.deployment.portal.dto.RegisterServiceRequest;
import com.deployment.portal.service.TerraformService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deploy")
public class DeploymentController {

    private final TerraformService terraformService;

    public DeploymentController(TerraformService terraformService) {
        this.terraformService = terraformService;
    }

    @PostMapping("/register-service")
    public String registerService(@RequestBody RegisterServiceRequest request) {

        terraformService.createInfrastructure(request);

        return "Service registered successfully";
    }
}