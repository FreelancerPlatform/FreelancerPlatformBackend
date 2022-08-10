package com.flag.freelancerplatform.controller;

import com.flag.freelancerplatform.model.Job;
import com.flag.freelancerplatform.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class RecommendationController {

	private RecommendationService recommendationService;
	private final int limit = 50;

	@Autowired
	public RecommendationController(RecommendationService recommendationService) {
		this.recommendationService = recommendationService;
	}

	@GetMapping(value = "/recommendation")
	public List<Job> getJobsByRecommendation(Principal principal) {
		String email = principal.getName();
		return recommendationService.getRecommendation(email, limit);
	}
}