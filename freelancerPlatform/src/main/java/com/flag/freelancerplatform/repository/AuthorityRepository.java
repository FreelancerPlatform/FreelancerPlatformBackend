package com.flag.freelancerplatform.repository;


import com.flag.freelancerplatform.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority findAuthorityByEmail(String email);
}
