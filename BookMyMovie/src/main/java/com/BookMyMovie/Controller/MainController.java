package com.BookMyMovie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BookMyMovie.ModelDB.modelDB;
import com.BookMyMovie.Service.MainCtrlService;
import com.BookMyMovie.entity.Movie;
import com.BookMyMovie.entity.Theaters;


@RestController
@RequestMapping("/BookMyMovie")
public class MainController {

	
	@Autowired
	MainCtrlService mainCtrlService;
	//Refresh End point
	@GetMapping("/FetchData")
	public ResponseEntity<String> getAllDataOfINOX() {
		return mainCtrlService.getINOXdata();
	}

	
	
	@GetMapping("/{location}")
	public List<Movie> getMoviesByLocation(@PathVariable String location) {
		return mainCtrlService.getMoviesOfSpecificLocation(location);
	}
	
	
	
	
}
