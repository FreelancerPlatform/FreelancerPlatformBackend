package com.flag.freelancerplatform.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ApplicantSkillKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private String skill_name;

    private String email;

    public ApplicantSkillKey() {}

    public ApplicantSkillKey(String skill_name, String email) {
        this.skill_name = skill_name;
        this.email = email;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public ApplicantSkillKey setSkill_name(String skill_name) {
        this.skill_name = skill_name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ApplicantSkillKey setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantSkillKey that = (ApplicantSkillKey) o;
        return skill_name.equals(that.skill_name) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill_name, email);
    }
}
