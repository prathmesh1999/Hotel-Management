package com.rating.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rating.entities.Rating;

public interface RatingRepo extends MongoRepository<Rating, String>{
	
	//custom finder method
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);

}
