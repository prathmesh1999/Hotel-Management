package com.user.service.UserService.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.service.UserService.entities.Rating;


@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	
	//get
	@GetMapping("/ratings/{ratingId}")
	Rating getRating(@PathVariable String ratingId);
	
	@GetMapping("/ratings/users/{userId}")
	List<Rating> getUserRating(@PathVariable String userId);
	
	// put
	@PutMapping("/ratings/{ratingId}")
	Rating updateRating(@PathVariable String ratingId,Rating rating);
	
	//post
	@PostMapping("ratings/")
	Rating createRating(Rating feedback);
	
//	delete
	@DeleteMapping("/ratings/{ratingId}")
	void deleteRating(@PathVariable String ratingId);
	
}
