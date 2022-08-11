package com.flag.freelancerplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user")
@JsonDeserialize(builder = User.Builder.class)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String email;

    private String name;

    @JsonIgnore
    private String password;

    private String gender;

    @JsonProperty("education_level")
    private String educationLevel;

    private boolean enabled;

    public User() {}

    private User(Builder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.password = builder.password;
        this.gender = builder.gender;
        this.educationLevel = builder.educationLevel;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public User setEducationLevel(String education_level) {
        this.educationLevel = education_level;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public User setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public static class Builder {
        @JsonProperty("email")
        private String email;

        @JsonProperty("name")
        private String name;

        @JsonProperty("password")
        private String password;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("education_level")
        private String educationLevel;

        @JsonProperty("enabled")
        private boolean enabled;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
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

        public Builder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
