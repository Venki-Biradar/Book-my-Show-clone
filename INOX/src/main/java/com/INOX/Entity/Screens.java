package com.INOX.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data  @AllArgsConstructor @NoArgsConstructor
public class Screens {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long ScreenId;
	
	private String ScreenName; 
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "theaterid") 
	@JsonBackReference 
	private Theaters theaterRef;
	
	@OneToMany(mappedBy = "screanRef",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	List<Seats> seats;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference 
	private Movie movieRef;
	 
	
	
	
}
