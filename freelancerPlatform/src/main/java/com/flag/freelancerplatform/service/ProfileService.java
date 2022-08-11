package com.flag.freelancerplatform.service;

import com.flag.freelancerplatform.model.*;
import com.flag.freelancerplatform.model.response.ApplicantResponseBody;
import com.flag.freelancerplatform.repository.ApplicantSkillRepository;
import com.flag.freelancerplatform.repository.ApplicationRepository;
import com.flag.freelancerplatform.repository.CertificationRepository;
import com.flag.freelancerplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private UserRepository userRepository;
    private CertificationRepository certificationRepository;
    private ApplicantSkillRepository applicantSkillRepository;

    private ApplicationRepository applicationRepository;

    @Autowired
    public ProfileService(UserRepository userRepository, CertificationRepository certificationRepository, ApplicantSkillRepository applicantSkillRepository, ApplicationRepository applicationRepository) {
        this.userRepository = userRepository;
        this.certificationRepository = certificationRepository;
        this.applicantSkillRepository = applicantSkillRepository;
        this.applicationRepository = applicationRepository;
    }

    public ApplicantResponseBody getProfile(String email) {
        User user = userRepository.findByEmail(email);
        List<ApplicantSkill> applicantSkills = applicantSkillRepository.findAllByUser(user);
        List<Certification> certifications =certificationRepository.findAllByUser(user);
        ApplicantResponseBody applicantResponseBody = new ApplicantResponseBody.Builder()
                .setEmail(user.getEmail())
                .setName(user.getName())
                .setGender(user.getGender())
                .setEducationLevel(user.getEducationLevel())
                .setSkill(applicantSkills.stream().map(skill -> skill.getApplicantSkillKey().getSkill_name()).collect(Collectors.toList()))
                .setCertification(certifications.stream().map(certification -> certification.getCertificationKey().getCertification_name()).collect(Collectors.toList()))
                .build();
        if (applicationRepository.findAllByEmailAndAndStatus(user, String.valueOf(ApplicationStatus.FINISHED)) != null) {
            applicantResponseBody.setRate(applicationRepository.findRateByEmail(user.getEmail()));
        }
        return  applicantResponseBody;
    }
}
