package com.flag.freelancerplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "application")
@JsonDeserialize(builder = Application.Builder.class)
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationID;

    private String status;

    @JsonIgnore
    // rate: 1 ~ 5
    private int rate;

    @ManyToOne
    @JoinColumn(name = "job_ID")
    @JsonProperty("job_ID")
    private Job jobID;

    @ManyToOne
    @JoinColumn(name = "email")
    private User email;

    public Application() {
    }

    public Application(Builder builder) {
        this.status = builder.status;
        this.rate = builder.rate;
        this.jobID = builder.jobID;
        this.email = builder.email;
    }

    public Long getApplicationID() {
        return applicationID;
    }

    public String getStatus() {
        return status;
    }

    public int getRate() {
        return rate;
    }

    public Job getJobID() {
        return jobID;
    }

    public User getEmail() {
        return email;
    }

    public static class Builder {
        @JsonProperty("application_ID")
        private Long applicationID;

        @JsonProperty("status")
        private String status;

        @JsonProperty("rate")
        private int rate;

        @JsonProperty("jobID")
        private Job jobID;

        @JsonProperty("email")
        private User email;

        public Builder setApplicationID(Long applicationID) {
            this.applicationID = applicationID;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setRate(int rate) {
            this.rate = rate;
            return this;
        }

        public Builder setJobID(Job jobID) {
            this.jobID = jobID;
            return this;
        }

        public Builder setEmail(User email) {
            this.email = email;
            return this;
        }

        public Application build() {
            return new Application(this);
        }
    }
}
