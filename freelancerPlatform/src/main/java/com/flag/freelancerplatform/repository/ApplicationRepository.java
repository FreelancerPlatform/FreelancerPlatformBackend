package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.Application;
import com.flag.freelancerplatform.model.Job;
import com.flag.freelancerplatform.model.response.ApplicationResponseBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Job findJobIDByApplicationID(Long applicationID);

    List<Application> findAllByJobID(Long jobID);

    Application findByApplicationID(Long applicationID);

    @Query(value = "SELECT AVG(AP.rate) " +
            "FROM Application AS AP " +
            "WHERE AP.email = ?1 ")
    double findRateByEmail(String email);

    @Query(value = "SELECT AP.applicationID AS application_ID, " +
            "AP.status AS status, " +
            "J.jobName AS job_name, " +
            "U.name AS applicant_name, " +
            "AP.email AS email " +
            "FROM Application AS AP " +
            "JOIN User AS U ON U.email = AP.email " +
            "JOIN Job AS J ON J.jobID = AP.jobID.jobID " +
            "WHERE AP.email = ?1 ")
    List<ApplicationResponseBody> findByApplicant(String email);

    @Query(value = "SELECT AP.applicationID AS application_ID, " +
            "AP.status AS status, " +
            "J.jobName AS job_name, " +
            "U.name AS applicant_name, " +
            "AP.email AS email " +
            "FROM Application AS AP " +
            "JOIN User AS U ON U.email = AP.email " +
            "JOIN Job AS J ON J.jobID = AP.jobID.jobID " +
            "WHERE AP.jobID.jobID = ?1 ")
    List<ApplicationResponseBody> findByJobID(Long jobID);
}
