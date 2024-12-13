package com.INOX.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.INOX.Entity.Movie;
import com.INOX.Entity.Seats;
import com.INOX.Entity.Theaters;
import com.INOX.Service.TheatersService;

@RestController
@RequestMapping("/INOX/theaters")
public class TheatersController 
{
	@Autowired   
	TheatersService theaterservice;
	
	@PostMapping("/add")
	public ResponseEntity<Theaters> savethe(@RequestBody Theaters theatre) {
		return ResponseEntity.ok(theaterservice.saveTheater(theatre));
	}
	@GetMapping("/all")
	public ResponseEntity<List<Theaters>> getall() {
		return ResponseEntity.ok(theaterservice.getall());
	}
	
	@PostMapping("/addMultiple")
	public ResponseEntity<List<Theaters>> saveMultiple(@RequestBody List<Theaters> list) {
		return ResponseEntity.ok(theaterservice.saveAllTheater(list));
	}
	
	@PutMapping("/updatemovie")
	public ResponseEntity<String> updatemovie(@RequestParam Long Theaterid, @RequestParam Long ScreenID ,@RequestBody Movie movie) {
		
		return theaterservice.updateOrSave(Theaterid,ScreenID,movie);
	}
	
	
	@PostMapping("/bookTicket")
	private String bookTicket(@RequestParam Long Theaterid,@RequestParam Long ScreenID,@RequestBody List<Seats> seat) {
		
		return "INOX movie tickets -"+theaterservice.bookIfNotSold(Theaterid,ScreenID,seat);
	}
	
	@PostMapping("/ResetBookingSeats")
	private String ResetBooking(@RequestParam Long ScreenID) {
		
		return theaterservice.reset(ScreenID);
	}
	
	
}
