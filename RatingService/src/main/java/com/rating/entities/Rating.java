package com.rating.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//@Document annotation use to create table in mongodb
@Document("user_ratings")
public class Rating {

	// did not use javax.persistance package to use @Id mongodb will automatically generate id (org.springframework.data.annotation.Id;)
	
	@Id
	private String ratingId;
	private String userId;
	private String hotelId;
	private int rating;
	private String feedback;
	
}
