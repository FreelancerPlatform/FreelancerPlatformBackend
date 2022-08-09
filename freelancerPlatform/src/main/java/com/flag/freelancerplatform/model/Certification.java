package com.flag.freelancerplatform.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "certification")
public class Certification implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CertificationKey certificationKey;

    // column email also is a forenign key of table user
    @MapsId("email")
    @ManyToOne
    private User user;

    public Certification() {}

    public Certification(CertificationKey certificationKey, User user) {
        this.certificationKey = certificationKey;
        this.user = user;
    }

    public CertificationKey getCertificationKey() {
        return certificationKey;
    }

    public User getUser() {
        return user;
    }

}
