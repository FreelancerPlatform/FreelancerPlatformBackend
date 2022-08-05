package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByJobType(String jobType);

    @Query(value = "WITH SKILLS (SKILL) AS " +
            "SELECT DISTINCT APS.applicantSkillKey.skill_name " +
            "FROM ApplicantSkill as APS " +
            "WHERE APS.applicantSkillKey.email = ?1 " +
            "SELECT * " +
            "FROM Job " +
            "WHERE Job.jobID IN " +
            "   (SELECT JBS.jobID " +
            "   FROM SKILLS, JobSkill as JBS " +
            "   WHERE JBS.jobSkillKey.skill_name IN SKILLS) ", nativeQuery = true)
    // get recommendation for this user according to his skills
    List<Job> getRecommendation(String email);
}
