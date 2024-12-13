package com.PVR.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PVR.Entity.Movie;
import com.PVR.Service.MovieService;

@RestController
@RequestMapping("/PVR/movies")
public class MovieController {

	@Autowired
	MovieService movieserv;
	
	@GetMapping("/all")
	public List<Movie> getallmovies() {
		return movieserv.getall();
	}
	
	
			
	
	
}
