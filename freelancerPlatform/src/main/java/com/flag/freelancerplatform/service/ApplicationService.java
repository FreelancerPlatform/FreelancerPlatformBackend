package com.flag.freelancerplatform.service;

import com.flag.freelancerplatform.exception.ApplicationCollisionException;
import com.flag.freelancerplatform.model.User;
import com.flag.freelancerplatform.repository.ApplicationRepository;
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
        private JobApplicationDateRepository jobApplicationDateRepository;

        @Autowired
        public ApplicationService(ApplicationRepository applicationRepository, JobApplicationDateRepository jobApplicationDateRepository) {
            this.applicationRepository = applicationRepository;
            this.jobApplicationDateRepository = jobApplicationDateRepository;
        }
        public List<Application> listByApplicant(String username) {
            return applicationRepository.findByApplicant(new User.Builder().setUsername(username).build());
        }

        public List<Application> listByJob(Long JobId) {
            return ApplicationRepository.findByJob(new Job.Builder().setId(JobId).build());
        }

        @Transactional(isolation = Isolation.SERIALIZABLE)
        public void add(Application Application) throws ApplicationCollisionException {
            Set<Long> jobIds = jobApplicationDateRepository.findByIdInAndDateBetween(Arrays.asList(Application.getJob().getId()), Application.getCheckinDate(), Application.getCheckoutDate().minusDays(1));
            if (!jobIds.isEmpty()) {
                throw new ApplicationCollisionException("Duplicate Application");
            }

//            List<JobReservedDate> reservedDates = new ArrayList<>();
//            for (LocalDate date = Application.getCheckinDate(); date.isBefore(Application.getCheckoutDate()); date = date.plusDays(1)) {
//                reservedDates.add(new JobReservedDate(new JobReservedDateKey(Application.getJob().getId(), date), Application.getJob()));
//            }
//            jobApplicationDateRepository.saveAll(reservedDates);
            ApplicationRepository.save(Application);
        }

        @Transactional(isolation = Isolation.SERIALIZABLE)
        public void delete(Long ApplicationId, String username) {
            Application Application = ApplicationRepository.findByIdAndApplicant(ApplicationId, new User.Builder().setUsername(username).build());
            if (Application == null) {
                throw new ApplicationNotFoundException("Application is not available");
            }
            for (LocalDate date = Application.getCheckinDate(); date.isBefore(Application.getCheckoutDate()); date = date.plusDays(1)) {
                JobApplicationDateRepository.deleteById(new JobReservedDateKey(Application.getJob().getId(), date));
            }
            ApplicationRepository.deleteById(ApplicationId);
        }

    }


