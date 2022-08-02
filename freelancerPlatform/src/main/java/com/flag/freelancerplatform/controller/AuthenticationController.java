package com.flag.freelancerplatform.controller;


import com.flag.freelancerplatform.model.Token;
import com.flag.freelancerplatform.model.User;
import com.flag.freelancerplatform.model.UserRole;
import com.flag.freelancerplatform.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate/applicant")
    public Token authenticateApplicant(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.ROLE_APPLICANT);
    }

    @PostMapping("/authenticate/employer")
    public Token authenticateEmployer(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.ROLE_EMPlOYER);
    }
}
