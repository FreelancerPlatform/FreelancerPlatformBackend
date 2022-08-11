package com.flag.freelancerplatform.service;

import com.flag.freelancerplatform.exception.JobNotExistException;
import com.flag.freelancerplatform.model.*;
import com.flag.freelancerplatform.model.request_reponse.JobInfo;
import com.flag.freelancerplatform.repository.JobRepository;
import com.flag.freelancerplatform.repository.JobSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

	private JobRepository jobRepository;
	private JobSkillRepository jobSkillRepository;

	@Autowired
	public JobService(JobRepository jobRepository, JobSkillRepository jobSkillRepository) {
		this.jobRepository = jobRepository;
		this.jobSkillRepository = jobSkillRepository;
	}

	public List<Job> allJobs(int limit) throws JobNotExistException {
		List<Job> jobs = jobRepository.findAll();

		List<Job> publicJobs = jobs.stream()
				.filter(job -> job.getStatus().equals(String.valueOf(JobStatus.PUBLIC)))
				.collect(Collectors.toList());

		// hide user information
		publicJobs = publicJobs.stream()
				.map(job ->
				{return job.setEmployer(new User.Builder().setEmail(job.getEmployer().getEmail()).build());})
				.collect(Collectors.toList());

		if (publicJobs.size() > limit) {
			return publicJobs.subList(0, limit);
		} else {
			return publicJobs;
		}
	}

	public List<Job> findByJobType(String jobType, int limit) throws JobNotExistException {
		List<Job> jobs = jobRepository.findByJobType(jobType);

		List<Job> publicJobs = jobs.stream().filter(job -> (job.getStatus().equals(String.valueOf(JobStatus.PUBLIC)))).collect(Collectors.toList());

		System.out.println(publicJobs);

		if (publicJobs == null ||publicJobs.size() == 0) {
			throw new JobNotExistException("Job of such type doesn't exist");
		} else if (publicJobs.size() > limit) {
			return publicJobs.subList(0, limit);
		} else {
			return publicJobs;
		}
	}

	public JobInfo findByJobId(Long jobID) throws JobNotExistException {
		Job job = jobRepository.findByJobID(jobID);

		if (job == null || !job.getStatus().equals(String.valueOf(JobStatus.PUBLIC))) {
			throw new JobNotExistException("Job with such ID doesn't exist");
		}

		List<JobSkill> jobSkills = jobSkillRepository.findAllByJob(job);

		List<String> skills = jobSkills.stream().map(jobSkill -> {return jobSkill.getJobSkillKey().getSkill_name();}).collect(Collectors.toList());

		JobInfo jobInfo = new JobInfo.Builder()
				.setJobName(job.getJobName())
				.setJobType(job.getJobType())
				.setContent(job.getContent())
				.setLocation(job.getLocation())
				.setSalary(job.getSalary())
				.setEmail(job.getEmployer().getEmail())
				.setSkill(skills)
				.build();

		return jobInfo;
	}

}