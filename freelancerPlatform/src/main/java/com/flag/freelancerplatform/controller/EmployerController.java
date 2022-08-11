package com.flag.freelancerplatform.controller;

import com.flag.freelancerplatform.model.Job;
import com.flag.freelancerplatform.model.request_reponse.JobInfo;
import com.flag.freelancerplatform.model.response.ApplicationResponseBody;
import com.flag.freelancerplatform.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class EmployerController {
    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping(value = "/employer/jobs")
    public List<Job> postedJobs(Principal principal) {
        return employerService.listByEmail(principal.getName());
    }

    @PostMapping("/employer/job")
    public void postJob(@RequestBody JobInfo jobInfo, Principal principal) {
        jobInfo = jobInfo.setEmail(principal.getName());
        employerService.addJob(jobInfo);
    }

    @GetMapping(value = "/employer/applications/{job_Id}")
    public List<ApplicationResponseBody> getApplications(@PathVariable Long job_Id) {
        return employerService.listApplicationsByJob(job_Id);
    }

    @PostMapping(value = "/employer/hire/{application_ID}")
    public void hire(@PathVariable Long application_ID) {
        employerService.hire(application_ID);
    }

    @PostMapping(value = "/employer/rate/{application_ID}/{rate}")
    public void rate(@PathVariable Long application_ID, @PathVariable int rate) {
        employerService.rate(application_ID, rate);
    }

    @PostMapping(value = "/employer/close/{job_ID}")
    public void closeJob(@PathVariable Long job_ID) {
        employerService.closeJob(job_ID);
    }
}