package com.flag.freelancerplatform.model.request_reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(builder = JobInfo.Builder.class)
public class JobInfo {

    @JsonProperty("job_name")
    private String jobName;

    @JsonProperty("location")
    private String location;

    @JsonProperty("salary")
    private int salary;

    @JsonProperty("content")
    private String content;

    @JsonProperty("email")
    private String email;

    @JsonProperty("job_type")
    private String jobType;

    @JsonProperty("skill")
    private List<String> skill;

    public JobInfo(Builder builder) {
        this.jobName = builder.jobName;
        this.location = builder.location;
        this.salary = builder.salary;
        this.content = builder.content;
        this.email = builder.email;
        this.jobType = builder.jobType;
        this.skill = builder.skill;
    }

    public String getJobName() {
        return jobName;
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

    public String getEmail() {
        return email;
    }

    public String getJobType() {
        return jobType;
    }

    public List<String> getSkill() {
        return skill;
    }

    public static class Builder {
        @JsonProperty("job_name")
        private String jobName;

        @JsonProperty("location")
        private String location;

        @JsonProperty("salary")
        private int salary;

        @JsonProperty("content")
        private String content;

        @JsonProperty("email")
        private String email;

        @JsonProperty("job_type")
        private String jobType;

        @JsonProperty("skill")
        private List<String> skill;

        public Builder setJobName(String jobName) {
            this.jobName = jobName;
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

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setJobType(String jobType) {
            this.jobType = jobType;
            return this;
        }

        public Builder setSkill(List<String> skill) {
            this.skill = skill;
            return this;
        }

        public JobInfo build() {
            return new JobInfo(this);
        }
    }
}
