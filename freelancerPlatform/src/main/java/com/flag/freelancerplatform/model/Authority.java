package com.flag.freelancerplatform.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "authority")
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String email;

    private String authority;

    public Authority() {}

    public Authority(String username, String authority) {
        this.email = username;
        this.authority = authority;
    }

    public String getUsername() {
        return email;
    }

    public Authority setUsername(String username) {
        this.email = username;
        return this;
    }

    public String getAuthority() {
        return authority;
    }

    public Authority setAuthority(String authority) {
        this.authority = authority;
        return this;
    }
}

