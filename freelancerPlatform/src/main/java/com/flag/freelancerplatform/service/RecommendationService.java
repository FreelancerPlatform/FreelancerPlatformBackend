package com.flag.freelancerplatform.service;

import com.flag.freelancerplatform.exception.JobNotExistException;
import com.flag.freelancerplatform.model.Job;
import com.flag.freelancerplatform.model.JobStatus;
import com.flag.freelancerplatform.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

	private JobRepository jobRepository;

	@Autowired
	public RecommendationService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	public List<Job> getRecommendation(String email, int limit) throws JobNotExistException {
		List<Job> jobs = jobRepository.getRecommendation(email);

		List<Job> availableJobs = new ArrayList<>();

		List<Job> publicJobs = jobs.stream().filter(job -> (job.getStatus().equals(String.valueOf(JobStatus.PUBLIC)))).collect(Collectors.toList());

		if (publicJobs.size() == 0) {
			throw new JobNotExistException("Job of such type doesn't exist");
		} else if (publicJobs.size() > limit) {
			return publicJobs.subList(0, limit);
		} else {
			return publicJobs;
		}
	}
}