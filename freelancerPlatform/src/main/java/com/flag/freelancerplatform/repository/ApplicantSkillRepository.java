package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.ApplicantSkill;
import com.flag.freelancerplatform.model.ApplicantSkillKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantSkillRepository extends JpaRepository<ApplicantSkill, ApplicantSkillKey> {

}
