package com.flag.freelancerplatform.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationResponseBody {

    private Long applicationID;

    private String status;

    private String jobName;

    private String applicantName;

    private String email;

    public ApplicationResponseBody() {
    }

    public ApplicationResponseBody(Long applicationID, String status, String jobName, String applicantName, String email) {
        this.applicationID = applicationID;
        this.status = status;
        this.jobName = jobName;
        this.applicantName = applicantName;
        this.email = email;
    }

    public ApplicationResponseBody(Builder builder) {
        this.applicationID = builder.applicationID;
        this.status = builder.status;
        this.jobName = builder.jobName;
        this.applicantName = builder.applicantName;
        this.email = builder.email;
    }

    public Long getApplicationID() {
        return applicationID;
    }

    public String getStatus() {
        return status;
    }

    public String getJobName() {
        return jobName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private Long applicationID;

        private String status;

        private String jobName;

        private String applicantName;

        private String email;

        public Builder setApplicationID(Long applicationID) {
            this.applicationID = applicationID;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setJobName(String jobName) {
            this.jobName = jobName;
            return this;
        }

        public Builder setApplicantName(String applicantName) {
            this.applicantName = applicantName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ApplicationResponseBody builder() {
            return new ApplicationResponseBody(this);
        }
    }
}
