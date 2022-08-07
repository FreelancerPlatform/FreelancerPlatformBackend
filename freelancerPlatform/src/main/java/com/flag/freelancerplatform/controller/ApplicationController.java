package com.flag.freelancerplatform.controller;

import com.flag.freelancerplatform.exception.InvalidApplicationDateException;
import com.flag.freelancerplatform.model.Application;
import com.flag.freelancerplatform.model.User;
import com.flag.freelancerplatform.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ApplicationController {
    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping(value = "/Applications")
    public List<Application> listApplications(Principal principal) {
        return applicationService.listByApplicant(principal.getName());
    }

    @PostMapping("/Applications")
    public void addApplication(@RequestBody Application Application, Principal principal) {
        Application.setApplicant(new User.Builder().setUsername(principal.getName()).build());
        ApplicationService.add(Application);
    }

    @DeleteMapping("/Applications/{ApplicationId}")
    public void deleteApplication(@PathVariable Long ApplicationId, Principal principal) {
        ApplicationService.delete(ApplicationId, principal.getName());
    }
}
