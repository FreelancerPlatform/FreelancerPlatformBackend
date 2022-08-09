package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.Certification;
import com.flag.freelancerplatform.model.CertificationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, CertificationKey> {
}
