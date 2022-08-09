package com.flag.freelancerplatform.service;

import com.flag.freelancerplatform.exception.UserAlreadyExistException;
import com.flag.freelancerplatform.model.*;
import com.flag.freelancerplatform.model.request.ApplicantRegisterRequestBody;
import com.flag.freelancerplatform.repository.ApplicantSkillRepository;
import com.flag.freelancerplatform.repository.AuthorityRepository;
import com.flag.freelancerplatform.repository.CertificationRepository;
import com.flag.freelancerplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService {
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;
    private CertificationRepository certificationRepository;
    private ApplicantSkillRepository applicantSkillRepository;

    @Autowired
    public RegisterService(UserRepository userRepository, AuthorityRepository authorityRepository,
                           PasswordEncoder passwordEncoder, CertificationRepository certificationRepository,
                           ApplicantSkillRepository applicantSkillRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.certificationRepository = certificationRepository;
        this.applicantSkillRepository = applicantSkillRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addApplicant(ApplicantRegisterRequestBody applicantRegisterRequestBody, UserRole role) throws UserAlreadyExistException {
        // set user's information, using builder pattern
        User user = new User.Builder()
                .setEmail(applicantRegisterRequestBody.getEmail())
                .setName(applicantRegisterRequestBody.getName())
                .setPassword(applicantRegisterRequestBody.getPassword())
                .setGender(applicantRegisterRequestBody.getGender())
                .setEducationLevel(applicantRegisterRequestBody.getEducationLevel()).build();
        if (userRepository.existsById(user.getEmail())) {
            throw new UserAlreadyExistException("User already exists");
        }

        // encode password and save user and authority
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        authorityRepository.save(new Authority(user.getEmail(), role.name()));

        // set certifications and save
        List<Certification> certificationList = new ArrayList<>();
        for (String input : applicantRegisterRequestBody.getCertification()) {
            CertificationKey certificationKey = new CertificationKey(input, user.getEmail());
            Certification certification = new Certification(certificationKey, user);
            certificationList.add(certification);
        }
        certificationRepository.saveAll(certificationList);

        // set jobSkills and save
        List<ApplicantSkill> applicantSkillList = new ArrayList<>();
        for (String input : applicantRegisterRequestBody.getSkill()) {
            ApplicantSkillKey applicantSkillKey = new ApplicantSkillKey(input, user.getEmail());
            ApplicantSkill applicantSkill = new ApplicantSkill(applicantSkillKey, user);
            applicantSkillList.add(applicantSkill);
        }
        applicantSkillRepository.saveAll(applicantSkillList);
    }


    public void addEmployer(User user, UserRole role) {
        if (userRepository.existsById(user.getEmail())) {
            throw new UserAlreadyExistException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        authorityRepository.save(new Authority(user.getEmail(), role.name()));
    }
}
