package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.Job;
import com.flag.freelancerplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByJobType(String jobType);

    List<Job> findByEmployer(User user);

    Job findByJobID(Long jobID);

    @Query(value = "SELECT J " +
            "FROM Job AS J " +
            "JOIN JobSkill AS JBS ON (JBS.jobSkillKey.jobID = J.jobID) " +
            "JOIN ApplicantSkill AS APS ON (JBS.jobSkillKey.skill_name = APS.applicantSkillKey.skill_name)" +
            "WHERE APS.applicantSkillKey.email = ?1")
    // get recommendation for this user according to his skills
    List<Job> getRecommendation(String email);
}
