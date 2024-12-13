package com.BookMyMovie.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @AllArgsConstructor @NoArgsConstructor
public class Screens {

	
	private Long ScreenId;
	
	private String ScreenName; //screenname
	
	@JsonBackReference
	private Theaters theaterRef;
	
	@JsonManagedReference
	List<Seats> seats;
	
	@JsonManagedReference
	private Movie movieRef;
	 
	
	
	
}
