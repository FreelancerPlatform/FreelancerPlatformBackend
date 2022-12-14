package com.flag.freelancerplatform.controller;

import com.flag.freelancerplatform.model.response.ApplicantResponseBody;
import com.flag.freelancerplatform.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile/{email}")
    public ApplicantResponseBody getProfile(@PathVariable String email) {
        return profileService.getProfile(email);
    }
}
