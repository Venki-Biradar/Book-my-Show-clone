package com.BookMyMovie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @AllArgsConstructor @NoArgsConstructor
public class Movie {


	private Long movieId;	
	private Long muid;	
	private String movieName;	
	private String language;
	
	@JsonBackReference
	private Screens screen;
	 
	


}
