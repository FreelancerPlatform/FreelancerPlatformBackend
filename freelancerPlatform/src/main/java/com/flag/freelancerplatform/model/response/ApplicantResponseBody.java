package com.flag.freelancerplatform.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(builder = ApplicantResponseBody.Builder.class)
public class ApplicantResponseBody {

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("education_level")
    private String educationLevel;

    @JsonProperty("certification")
    private List<String> certification;

    @JsonProperty("skill")
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

    public static class Builder {
        @JsonProperty("email")
        private String email;

        @JsonProperty("name")
        private String name;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("education_level")
        private String educationLevel;

        @JsonProperty("certification")
        private List<String> certification;

        @JsonProperty("skill")
        private List<String> skill;

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

        public ApplicantResponseBody build() {
            return new ApplicantResponseBody(this);
        }
    }
}
