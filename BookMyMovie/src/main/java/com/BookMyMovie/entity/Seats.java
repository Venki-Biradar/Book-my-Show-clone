package com.BookMyMovie.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data  @AllArgsConstructor @NoArgsConstructor
public class Seats {
	
	private Long seat_id;
	private String seat_name; 	
	private Double price; 
	private String status; 	
	
	@JsonBackReference
	private Screens screanRef;

	public Seats(String seat_name) {
		this.seat_name = seat_name;
	}
	
 
	 
}
