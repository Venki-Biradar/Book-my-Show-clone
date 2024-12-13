package com.PVR.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data  @AllArgsConstructor @NoArgsConstructor
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long movieId;
	
	private Long muid;
	
	private String movieName;
	
	private String language;
	
	
	  @OneToOne(mappedBy = "movieRef")
	  @JsonBackReference 
	  private Screens screen;
	 
	


}
