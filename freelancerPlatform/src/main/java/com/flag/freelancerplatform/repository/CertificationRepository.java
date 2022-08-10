package com.flag.freelancerplatform.repository;

import com.flag.freelancerplatform.model.Certification;
import com.flag.freelancerplatform.model.CertificationKey;
import com.flag.freelancerplatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, CertificationKey> {
    List<Certification> findAllByUser(User user);
}
