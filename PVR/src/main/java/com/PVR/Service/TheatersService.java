package com.PVR.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PVR.Entity.Movie;
import com.PVR.Entity.Screens;
import com.PVR.Entity.Seats;
import com.PVR.Entity.Theaters;
import com.PVR.Repo.TheatersRepo;

@Service
public class TheatersService {

	@Autowired
	TheatersRepo theatersRepo;
	
	public Theaters getbyIg(Long id)  {
		return theatersRepo.findById(id).orElseThrow();
	} 
	
	public List<Theaters> getall() {
		return theatersRepo.findAll();
	}
	public Theaters saveTheater(Theaters theater) {
		return theatersRepo.save(theater);
	}
	public List<Theaters> saveAllTheater(List<Theaters> theaters) {
		return theatersRepo.saveAll(theaters);
	}

	
	@Autowired
	MovieService movieserv;
	public ResponseEntity<String> updateOrSave(Long theaterid, Long screenID, Movie movie) {
		Screens TargetScreen=theatersRepo.findById(theaterid).get().getScreens().stream()
				.filter(s->  s.getScreenId()==screenID).findFirst().get();
		Long ExistingMovieId=TargetScreen.getMovieRef().getMovieId();
		
		movie.setMovieId(ExistingMovieId);	
		movieserv.saveOrUpdate(movie);
		ResponseEntity<String> completeResponse=new ResponseEntity<>("Susseccfully movie update on screenID= "+screenID, HttpStatus.OK);
		return completeResponse;
	}

@Autowired
ScreenService screenservice;
@Autowired
SeatsService seatserv;

	public String bookIfNotSold(Long theaterid, Long screenID, List<Seats> seat) {
		long i=0;
		Screens TargetScreen=theatersRepo.findById(theaterid).get().getScreens().stream()
							.filter(s->  s.getScreenId()==screenID).findFirst().get();
		List<Seats> BookedSeats =TargetScreen.getSeats();
		
		for(Seats s:seat)
		{
		i+=BookedSeats.stream().filter(bookedSeats->bookedSeats.getSeat_name().equals(s.getSeat_name())).count();
		}
		
		
		if(i==0) 
		{
			seat.forEach(s->
			{
			s.setScreanRef(TargetScreen);
			seatserv.saveSeats(s);
			});
			
			
			
			return "booked successfully";
		}
		else 
		{
			
			String response="seats of "+
			seat.stream().map(s->s.getSeat_name()).collect(Collectors.toList()).toString()+" is already booked";
			return response;
		}
		
	}

	

	public String reset(Long screenID) {
		
		
		Screens screen=screenservice.getbyId(screenID);		
		List<Seats> seats=screen.getSeats();
		
		List<Long> ids= seats.stream().map(a->a.getSeat_id()).collect(Collectors.toList());
		seats.removeIf(x->ids.contains(x.getSeat_id()));
		
		screen.setSeats(seats);
		screenservice.saveScreen(screen);
		
		return "reset";
		
	}
}
		
	

