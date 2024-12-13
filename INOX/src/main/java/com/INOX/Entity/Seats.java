package com.INOX.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data  @AllArgsConstructor @NoArgsConstructor
public class Seats {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long seat_id;
	
	private String seat_name; 	
	private Double price; 
	private String status; 	

	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screan_id")
	@JsonBackReference
	private Screens screanRef;
	
 
	 
}
