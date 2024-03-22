package com.hotel.HotelService.controller;

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

import com.hotel.HotelService.entities.Hotel;
import com.hotel.HotelService.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/")
	public ResponseEntity<Hotel> create(@RequestBody Hotel hotel){
		Hotel createHotel = hotelService.create(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(createHotel);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getall(){
		List<Hotel> hotels = hotelService.getAll();
		return ResponseEntity.ok(hotels);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String id){
		Hotel hotel = hotelService.get(id);
		return ResponseEntity.ok(hotel);
	}	
}
