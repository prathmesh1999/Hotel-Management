package com.user.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	@Test
	void contextLoads() {
	}
	

	@Test
	void createRating() {
		Rating rating = new Rating();
		rating.setRating(9);
		rating.setUserId("");
		rating.setHotelId("");
		rating.setFeedback("must try!!");
		Rating createRating = ratingService.createRating(rating);
		System.out.println("Rating created");
		
	}
	
	

}
