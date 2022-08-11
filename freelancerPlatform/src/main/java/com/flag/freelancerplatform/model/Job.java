package com.flag.freelancerplatform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "job")
@JsonDeserialize(builder = Job.Builder.class)
public class Job implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobID;

    private String location;

    private int salary;

    private String content;

    @JsonProperty("job_name")
    private String jobName;

    private String status;

    @ManyToOne
    @JoinColumn(name = "email")
    private User employer;

    @JsonProperty("job_type")
    private String jobType;

    public Job() {
    }

    public Job(Builder builder) {
        this.jobID = builder.jobID;
        this.location = builder.location;
        this.salary = builder.salary;
        this.content = builder.content;
        this.jobName = builder.jobName;
        this.status = builder.status;
        this.employer = builder.employer;
        this.jobType = builder.jobType;
    }

    public Long getJobID() {
        return jobID;
    }

    public String getLocation() {
        return location;
    }

    public int getSalary() {
        return salary;
    }

    public String getContent() {
        return content;
    }

    public String getJobName() {
        return jobName;
    }

    public String getStatus() {
        return status;
    }

    public Job setStatus(String status) {
        this.status = status;
        return this;
    }

    public Job setEmployer(User employer) {
        this.employer = employer;
        return this;
    }

    public User getEmployer() {
        return employer;
    }

    public String getJobType() {
        return jobType;
    }

    public static class Builder {
        @JsonProperty("job_ID")
        private Long jobID;

        @JsonProperty("location")
        private String location;

        @JsonProperty("salary")
        private int salary;

        @JsonProperty("content")
        private String content;

        @JsonProperty("job_name")
        private String jobName;

        @JsonProperty("status")
        private String status;

        @JsonProperty("employer")
        private User employer;

        @JsonProperty("job_type")
        private String jobType;

        public Builder setJobID(Long jobID) {
            this.jobID = jobID;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setSalary(int salary) {
            this.salary = salary;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setJobName(String jobName) {
            this.jobName = jobName;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setEmployer(User employer) {
            this.employer = employer;
            return this;
        }

        public Builder setJobType(String jobType) {
            this.jobType = jobType;
            return this;
        }

        public Job build() {
            return new Job(this);
        }
    }
}
