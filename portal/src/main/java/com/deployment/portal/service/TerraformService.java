package com.deployment.portal.service;

import com.deployment.portal.dto.RegisterServiceRequest;
import org.springframework.stereotype.Service;

@Service
public class TerraformService {

    public void createInfrastructure(RegisterServiceRequest request) {

        try {

            ProcessBuilder builder = new ProcessBuilder(
                    "terraform",
                    "apply",
                    "-auto-approve",
                    "-var", "service_name=" + request.getServiceName(),
                    "-var", "team_name=" + request.getTeamName(),
                    "-var", "repo_url=" + request.getRepoUrl()
            );

            builder.directory(new java.io.File("../terraform"));

            Process process = builder.start();

            process.waitFor();

        } catch (Exception e) {
            throw new RuntimeException("Terraform execution failed", e);
        }
    }
}