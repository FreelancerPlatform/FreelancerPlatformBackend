package com.flag.freelancerplatform.service;

import com.flag.freelancerplatform.model.*;
import com.flag.freelancerplatform.model.request_reponse.JobInfo;
import com.flag.freelancerplatform.model.response.ApplicationResponseBody;
import com.flag.freelancerplatform.repository.ApplicationRepository;
import com.flag.freelancerplatform.repository.JobRepository;
import com.flag.freelancerplatform.repository.JobSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerService {
    private JobRepository jobRepository;
    private JobSkillRepository jobSkillRepository;
    private ApplicationRepository applicationRepository;

    @Autowired
    public EmployerService(JobRepository jobRepository, JobSkillRepository jobSkillRepository, ApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.jobSkillRepository = jobSkillRepository;
        this.applicationRepository = applicationRepository;
    }

    public List<Job> listByEmail(String email) {
        return jobRepository.findByEmployer(new User.Builder().setEmail(email).build());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addJob(JobInfo jobInfo) {
        User employer = new User.Builder().setEmail(jobInfo.getEmail()).build();
        Job job = new Job.Builder()
                .setJobName(jobInfo.getJobName())
                .setJobType(jobInfo.getJobType())
                .setEmployer(employer)
                .setContent(jobInfo.getContent())
                .setLocation(jobInfo.getLocation())
                .setSalary(jobInfo.getSalary())
                .setStatus(String.valueOf(JobStatus.PUBLIC))
                .build();
        job = jobRepository.save(job);
        List<JobSkill> jobSkills = new LinkedList<>();
        for (String skill : jobInfo.getSkill()) {
            jobSkills.add(new JobSkill(new JobSkillKey(skill, job.getJobID()), job));
        }
        jobSkillRepository.saveAll(jobSkills);
    }

    public List<ApplicationResponseBody> listApplicationsByJob(Long job_id) {
        return applicationRepository.findByJobID(job_id);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void hire(Long application_id) {
        Application target = applicationRepository.findByApplicationID(application_id);
        Job job = target.getJobID();
        List<Application> applications = applicationRepository.findAllByJobID(job);
        for (Application application : applications) {
            if (application.getApplicationID() != application_id) {
                application.setStatus(String.valueOf(ApplicationStatus.REJECTED));
            }
            else {
                application.setStatus(String.valueOf(ApplicationStatus.HIRED));
            }
        }
        job.setStatus(String.valueOf(JobStatus.HIRED));
        applicationRepository.saveAll(applications);
        jobRepository.save(job);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void rate(Long application_id, int rate) {
        Application application = applicationRepository.findByApplicationID(application_id);
        application.setRate(rate);
        application.setStatus(String.valueOf(ApplicationStatus.FINISHED));
        Job job = application.getJobID().setStatus(String.valueOf(ApplicationStatus.FINISHED));
        applicationRepository.save(application);
        jobRepository.save(job);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void closeJob(Long job_id) {
        Job job = jobRepository.findByJobID(job_id);
        job.setStatus(String.valueOf(JobStatus.CLOSED));
        job = jobRepository.save(job);
        List<Application> applications = applicationRepository.findAllByJobID(job);
        for (Application application : applications) {
            application.setStatus(String.valueOf(ApplicationStatus.CLOSED));
        }
        applicationRepository.saveAll(applications);
    }
}
