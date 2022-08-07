package com.flag.freelancerplatform.service;

import com.flag.freelancerplatform.exception.ApplicationCollisionException;
import com.flag.freelancerplatform.model.ApplicationStatus;
import com.flag.freelancerplatform.model.Job;
import com.flag.freelancerplatform.model.User;
import com.flag.freelancerplatform.model.response.ApplicationResponseBody;
import com.flag.freelancerplatform.repository.ApplicationRepository;
import com.flag.freelancerplatform.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.flag.freelancerplatform.model.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;



    @Service
    public class ApplicationService {
        private ApplicationRepository applicationRepository;
        private JobRepository jobRepository;

        @Autowired
        public ApplicationService(ApplicationRepository applicationRepository, JobRepository jobRepository) {
            this.applicationRepository = applicationRepository;
            this.jobRepository = jobRepository;
        }
        public List<ApplicationResponseBody> listByApplicant(String email) {
            return applicationRepository.findByApplicant(email);
        }

        @Transactional(isolation = Isolation.SERIALIZABLE)
        public void addApplication(Long jobID, String email) {
            Application application = new Application.Builder()
                            .setEmail(new User.Builder().setEmail(email).build())
                            .setJobID(new Job.Builder().setJobID(jobID).build())
                            .setStatus(String.valueOf(ApplicationStatus.PENDING))
                            .build();
            applicationRepository.save(application);
        }

        @Transactional(isolation = Isolation.SERIALIZABLE)
        public void delete(Long applicationID) {
            applicationRepository.deleteById(applicationID);
        }

    }


