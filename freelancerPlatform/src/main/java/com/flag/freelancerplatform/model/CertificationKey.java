package com.flag.freelancerplatform.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CertificationKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private String certification_name;

    private String email;

    public CertificationKey() {}

    public CertificationKey(String certification_name, String email) {
        this.certification_name = certification_name;
        this.email = email;
    }

    public String getCertification_name() {
        return certification_name;
    }

    public CertificationKey setCertification_name(String certification_name) {
        this.certification_name = certification_name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CertificationKey setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificationKey that = (CertificationKey) o;
        return certification_name.equals(that.certification_name) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(certification_name, email);
    }

}
