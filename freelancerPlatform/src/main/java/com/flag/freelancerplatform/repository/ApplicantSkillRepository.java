package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.ApplicantSkill;
import com.flag.freelancerplatform.model.ApplicantSkillKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.flag.freelancerplatform.model.response.ApplicationResponseBody;

@Repository
public interface ApplicantSkillRepository extends JpaRepository<ApplicantSkill, ApplicantSkillKey> {

    @Query(value = "SELECT AP.applicationID AS application_ID, " +
            "AP.status, " +
            "Job.jobName AS job_name, " +
            "User.name AS applicant_name, " +
            "?1 " +
            "FROM Application AS AP " +
            "LEFT JOIN User ON User.email = AP.email " +
            "LEFT JOIN Job ON Job.jobID = AP.jobID.jobID " +
            "WHERE AP.email = ?1 ")
    ApplicationResponseBody findByApplicant(String email);

    @Query(value = "SELECT AP.applicationID AS application_ID, " +
            "AP.status, " +
            "Job.jobName AS job_name, " +
            "User.name AS applicant_name, " +
            "?1 " +
            "FROM Application AS AP " +
            "LEFT JOIN User ON User.email = AP.email " +
            "LEFT JOIN Job ON Job.jobID = AP.jobID.jobID " +
            "WHERE AP.jobID.jobID = ?1 ")
    ApplicationResponseBody findByJobID(Long jobID);
}
