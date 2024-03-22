package com.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.entities.Rating;
import com.rating.service.RatingService;
import com.rating.serviceImpl.RatingServiceImpl;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	
	//create rating
	@PostMapping("/")
	public ResponseEntity<Rating> create(@RequestBody Rating rating){
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ratingService.createRating(rating));
	}
	
	//get all
	@GetMapping("/")
	public ResponseEntity<List<Rating>> getRatings(){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRating());
		
	}
	
	//rating get by userid
	@GetMapping("/users/{id}")
	public ResponseEntity<List<Rating>> getRatingByUser(@PathVariable String id){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(id));
	}
	
	//get by hotelid
	@GetMapping("/hotels/{id}")
	public ResponseEntity<List<Rating>> getRatingByHotel(@PathVariable String id){
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(id));
	}

}
