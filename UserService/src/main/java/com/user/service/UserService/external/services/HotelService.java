package com.user.service.UserService.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.service.UserService.entities.Hotel;

@Service
@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
	
	 @GetMapping("/hotels/{hotelId}")
	 Hotel getHotel(@PathVariable String hotelId);

}
