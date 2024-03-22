package com.hotel.HotelService.payload;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
	
	private String message;
	private boolean sucess;
	private HttpStatus status;

}
