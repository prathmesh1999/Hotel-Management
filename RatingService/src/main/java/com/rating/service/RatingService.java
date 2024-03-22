package com.rating.service;

import java.util.List;

import com.rating.entities.Rating;

public interface RatingService {
	
	//create
	Rating createRating(Rating rating);
	
	//get all rating
	List<Rating> getRating();
	
	//user wise rating
	List<Rating> getRatingByUserId(String userId);
	
	//get all by hotel
	List<Rating> getRatingByHotelId(String hotelId);

}
