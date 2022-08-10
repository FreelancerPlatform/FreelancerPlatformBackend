package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.ApplicantSkill;
import com.flag.freelancerplatform.model.ApplicantSkillKey;
import com.flag.freelancerplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantSkillRepository extends JpaRepository<ApplicantSkill, ApplicantSkillKey> {
    List<ApplicantSkill> findAllByUser(User user);
}
