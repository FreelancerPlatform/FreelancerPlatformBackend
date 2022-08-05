package com.flag.freelancerplatform.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApplicantRegisterRequestBody {

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("password")
    private String password;

    @JsonProperty("education_level")
    private String educationLevel;

    @JsonProperty("certification")
    private List<String> certification;

    @JsonProperty("skill")
    private List<String> skill;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
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
}
