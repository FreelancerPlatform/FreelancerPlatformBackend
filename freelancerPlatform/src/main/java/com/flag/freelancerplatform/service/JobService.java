package com.flag.freelancerplatform.service;

import com.flag.freelancerplatform.exception.JobNotExistException;
import com.flag.freelancerplatform.model.Job;
import com.flag.freelancerplatform.model.JobStatus;
import com.flag.freelancerplatform.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class JobService {

	private JobRepository jobRepository;

	@Autowired
	public JobService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	public List<Job> allJobs(int limit) throws JobNotExistException {
		List<Job> jobs = jobRepository.findAll();

		List<Job> publicJobs = jobs.stream().filter(job -> (job.getStatus().equals(JobStatus.PUBLIC))).collect(Collectors.toList());

		if (publicJobs.size() == 0) {
			throw new JobNotExistException("Job of such type doesn't exist");
		} else if (publicJobs.size() > limit) {
			return publicJobs.subList(0, limit);
		} else {
			return publicJobs;
		}
	}

	public List<Job> findByJobType(String jobType, int limit) throws JobNotExistException {
		List<Job> jobs = jobRepository.findByJobType(jobType);

		if (jobs == null || jobs.size() == 0) {
			throw new JobNotExistException("Job of such type doesn't exist");
		}

		List<Job> publicJobs = jobs.stream().filter(job -> (job.getStatus().equals(JobStatus.PUBLIC))).collect(Collectors.toList());

		if (publicJobs.size() == 0) {
			throw new JobNotExistException("Job of such type doesn't exist");
		} else if (publicJobs.size() > limit) {
			return publicJobs.subList(0, limit);
		} else {
			return publicJobs;
		}
	}

	public Job findByJobId(Long jobID) throws JobNotExistException {
		Job job = jobRepository.findByJobID(jobID);

		if (job == null || !job.getStatus().equals("PUBLIC")) {
			throw new JobNotExistException("Job with such ID doesn't exist");
		}

		return job;
	}

}