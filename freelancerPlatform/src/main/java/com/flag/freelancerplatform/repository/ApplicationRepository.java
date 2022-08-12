package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.Application;
import com.flag.freelancerplatform.model.Job;
import com.flag.freelancerplatform.model.User;
import com.flag.freelancerplatform.model.response.ApplicationResponseBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByJobID(Job jobID);

    Application findByApplicationID(Long applicationID);

    List<Application> findAllByEmailAndAndStatus(User user, String status);

    @Query(value = "SELECT AVG(AP.rate) " +
            "FROM Application AS AP " +
            "WHERE AP.email.email = ?1 AND AP.status = 'FINISHED' " +
            "GROUP BY AP.email")
    Double findRateByEmail(String email);

    @Query(value = "SELECT new com.flag.freelancerplatform.model.response" +
            ".ApplicationResponseBody(AP.applicationID, AP.status, J.jobName" +
            ", U.name, AP.email.email) " +
            "FROM Application AS AP, " +
            "User AS U, " +
            "Job AS J " +
            "WHERE AP.email.email = ?1 " +
            "AND U.email = AP.email.email " +
            "AND J.jobID = AP.jobID.jobID ")
    List<ApplicationResponseBody> findByApplicant(String email);

    @Query(value = "SELECT new com.flag.freelancerplatform.model.response" +
            ".ApplicationResponseBody(AP.applicationID, AP.status, J.jobName" +
            ", U.name, AP.email.email) " +
            "FROM Application AS AP, " +
            "User AS U, " +
            "Job AS J " +
            "WHERE AP.jobID.jobID = ?1 " +
            "AND U.email = AP.email " +
            "AND J.jobID = AP.jobID.jobID")
    List<ApplicationResponseBody> findByJobID(Long jobID);
}