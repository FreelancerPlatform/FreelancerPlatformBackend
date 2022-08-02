package com.flag.freelancerplatform.controller;


import com.flag.freelancerplatform.model.User;
import com.flag.freelancerplatform.model.UserRole;
import com.flag.freelancerplatform.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register/applicant")
    public void addApplicant(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_APPLICANT);
    }

    @PostMapping("/register/employer")
    public void addEmployer(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_EMPlOYER);
    }
}
