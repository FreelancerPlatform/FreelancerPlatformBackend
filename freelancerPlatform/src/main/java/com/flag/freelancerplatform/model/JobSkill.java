package com.flag.freelancerplatform.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "job_skill")
public class JobSkill implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private JobSkillKey jobSkillKey;

    // column jobID also is a forenign key of table job
    @MapsId("jobID")
    @ManyToOne
    private Job job;

    public JobSkill() {}

    public JobSkill(JobSkillKey jobSkillKey, Job job) {
        this.jobSkillKey = jobSkillKey;
        this.job = job;
    }

    public JobSkillKey getJobSkillKey() {
        return jobSkillKey;
    }

    public Job getJob() {
        return job;
    }
}
