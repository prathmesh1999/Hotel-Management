package com.hotel.HotelService.service;

import java.util.List;

import com.hotel.HotelService.entities.Hotel;

public interface HotelService {
	
	Hotel create(Hotel hotel);
	
	List<Hotel> getAll();
	
	Hotel get(String id);
	

}
