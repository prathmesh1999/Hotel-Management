package com.user.service.UserService.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exception.ResourceNotFoundException;
import com.user.service.UserService.external.services.HotelService;
import com.user.service.UserService.external.services.RatingService;
import com.user.service.UserService.repository.UserRepository;
import com.user.service.UserService.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RatingService ratingService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// set random userid
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> findAllUsers = userRepository.findAll();
		List<User> users = new ArrayList<>();

		for (User user : findAllUsers) {
			System.out.println("User id is " + user.getUserId());

			// Fetch ratings of the current user from the rating service
			List<Rating> ratingsOfUser = ratingService.getUserRating(user.getUserId());
			logger.info("{}", ratingsOfUser);

			if (ratingsOfUser != null) {
				// Fetch hotels for each rating from the hotel service
				ratingsOfUser.stream().map(rating -> {

					// API call to hotel service to get hotel
					Hotel hotel = hotelService.getHotel(rating.getHotelId());

					// Set the hotel to the rating
					rating.setHotel(hotel);

					return rating;
				}).collect(Collectors.toList());

				// Set the ratings to the user
				user.setRatings(ratingsOfUser);

				// Add the user to the list
				users.add(user);
			}
		}

		return users;
	}

	@Override
	public User getUser(String id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user with this id not found on server " + id));
		// fetch rating of above user from rating service
//		Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(),Rating[].class);
		List<Rating> ratingsOfUser = ratingService.getUserRating(user.getUserId());
		logger.info("{}", ratingsOfUser);

		ratingsOfUser.stream().map(rating -> {
			// api call to hotel serviceto get hotel
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());

			// set the hotel to rating
			rating.setHotel(hotel);
			// return rating
			return rating;
		}).collect(Collectors.toList());

		user.setRatings(ratingsOfUser);
		return user;
	}

}
