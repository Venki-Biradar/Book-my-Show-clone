package com.BookMyMovie.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data  @AllArgsConstructor @NoArgsConstructor
public class Theaters 
{
	private Long TheaterId;
	private String Origin ;
	private String TheaterName; //theaterName
	private String Location;
	@JsonManagedReference
    private List<Screens> screens;	
}
