package com.flag.freelancerplatform.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

public class ApplicantResponseBody {

    private String email;

    private String name;

    private String gender;

    private String educationLevel;

    private Double rate;

    private List<String> certification;

    private List<String> skill;

    public ApplicantResponseBody(Builder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.gender = builder.gender;
        this.educationLevel = builder.educationLevel;
        this.certification = builder.certification;
        this.skill = builder.skill;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public List<String> getCertification() {
        return certification;
    }

    public List<String> getSkill() {
        return skill;
    }

    public Double getRate() {
        return rate;
    }

    public ApplicantResponseBody setRate(Double rate) {
        this.rate = rate;
        return this;
    }

    public static class Builder {
        private String email;

        private String name;

        private String gender;

        private String educationLevel;

        private List<String> certification;

        private List<String> skill;

        private Double rate;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setEducationLevel(String educationLevel) {
            this.educationLevel = educationLevel;
            return this;
        }

        public Builder setCertification(List<String> certification) {
            this.certification = certification;
            return this;
        }

        public Builder setSkill(List<String> skill) {
            this.skill = skill;
            return this;
        }

        public Builder setRate(double rate) {
            this.rate = rate;
            return this;
        }

        public ApplicantResponseBody build() {
            return new ApplicantResponseBody(this);
        }
    }
}
