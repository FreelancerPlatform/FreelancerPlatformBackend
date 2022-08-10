package com.flag.freelancerplatform.controller;

import com.flag.freelancerplatform.model.Job;
import com.flag.freelancerplatform.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {

	private JobService jobService;
	private final int limit = 50;

	@Autowired
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping(value = "/jobs")
	public List<Job> getJobs() {
		return jobService.allJobs(limit);
	}

	@GetMapping(value = "/jobs/{job_type}")
	public List<Job> getJobsByType(@PathVariable String job_type) {
		return jobService.findByJobType(job_type, limit);
	}

	@GetMapping(value = "/jobs/{job_ID}")
	public Job getJobByID(@PathVariable Long job_ID) {
		return jobService.findByJobId(job_ID);
	}

}