package com.PVR.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data  @AllArgsConstructor @NoArgsConstructor
@Table
public class Theaters 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long TheaterId;

	@Column(nullable = false) 
	private String Origin = "PVR";
	
	private String TheaterName;
	private String Location;
	
	@OneToMany(mappedBy = "theaterRef",cascade = CascadeType.ALL, orphanRemoval = true) 
	@JsonManagedReference
    private List<Screens> screens;

	
}
