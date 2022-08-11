package com.flag.freelancerplatform.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "applicant_skill")
public class ApplicantSkill implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ApplicantSkillKey applicantSkillKey;

    // column email also is a forenign key of table user
    @MapsId("email")
    @ManyToOne
    private User user;

    public ApplicantSkill() {}

    public ApplicantSkill(ApplicantSkillKey applicantSkillKey, User user) {
        this.applicantSkillKey = applicantSkillKey;
        this.user = user;
    }

    public ApplicantSkillKey getApplicantSkillKey() {
        return applicantSkillKey;
    }

    public User getUser() {
        return user;
    }
}
