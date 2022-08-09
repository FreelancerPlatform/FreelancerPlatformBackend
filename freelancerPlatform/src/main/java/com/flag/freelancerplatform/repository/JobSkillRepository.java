package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.JobSkill;
import com.flag.freelancerplatform.model.JobSkillKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillKey> {
}
