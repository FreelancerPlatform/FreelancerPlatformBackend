package com.flag.freelancerplatform.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JobSkillKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private String skill_name;

    private Long jobID;

    public JobSkillKey() {}

    public JobSkillKey(String skill_name, Long jobID) {
        this.skill_name = skill_name;
        this.jobID = jobID;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public JobSkillKey setSkill_name(String skill_name) {
        this.skill_name = skill_name;
        return this;
    }

    public Long getJobID() {
        return jobID;
    }

    public JobSkillKey setJobID(Long jobID) {
        this.jobID = jobID;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobSkillKey that = (JobSkillKey) o;
        return skill_name.equals(that.skill_name) && jobID.equals(that.jobID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill_name, jobID);
    }
}
