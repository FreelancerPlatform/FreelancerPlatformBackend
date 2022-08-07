package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.Application;
<<<<<<< Updated upstream
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.flag.freelancerplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByApplicant(User applicant);

    List<Application> findByJob(Job job);

    Application findByIdAndApplicant(Long id, User applicant); // for deletion

//    List<Application> findByJobAndCheckoutDateAfter(Job job, LocalDate date);
}

