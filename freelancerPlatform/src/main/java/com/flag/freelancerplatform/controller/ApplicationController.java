package com.flag.freelancerplatform.controller;

import com.flag.freelancerplatform.model.response.ApplicationResponseBody;
import com.flag.freelancerplatform.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class ApplicationController {
    private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping(value = "/Applications")
    public List<ApplicationResponseBody> listApplications(Principal principal) {
        return applicationService.listByApplicant(principal.getName());
    }

    @PostMapping("/Applications/{job_ID}")
    public void addApplication(@PathVariable Long job_ID, Principal principal) {
        applicationService.addApplication(job_ID, principal.getName());
    }

    @DeleteMapping("/Applications/{application_ID}")
    public void deleteApplication(@PathVariable Long application_ID) {
        applicationService.delete(application_ID);
    }
}
