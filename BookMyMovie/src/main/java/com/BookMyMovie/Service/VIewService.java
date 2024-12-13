package com.BookMyMovie.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.BookMyMovie.ModelDB.modelDB;
import com.BookMyMovie.entity.Movie;
import com.BookMyMovie.entity.Seats;
import com.BookMyMovie.entity.Theaters;

@Service
public class VIewService {

	public List<Movie> filtermoviesbasedOnlocation(String Location) {
		return modelDB.FilterBasedOnlocation(Location);
	}
	
	public List<Theaters> getTheatersByName_and_location(String movieName, String location) {
		return modelDB.getTheatersByMovieNameAndLocation(movieName, location);
	}
	
	public List<String> getsoldseatsnameByScreenIdAndTheaterId(String origin,Long theaterId, Long screenId) {
		return modelDB.getSoldTicketsbyTheaterIdandScreenId(origin,theaterId,screenId);
	}
	public Theaters getTheaterByIdandOrgin(Long theaterId, String origin) {
		return modelDB.GetTheaterByIdandOrgin(theaterId,origin);
	}
	
	
	//--------------i have defined seats of theaters ----------
	  public List<String> generateSeatLabels() {
	        List<String> seatLabels = new ArrayList<>();
	        for (char row = 'A'; row <= 'E'; row++) {
	            for (int col = 1; col <= 10; col++) {
	                seatLabels.add(row + String.valueOf(col));
	            }
	        }
	        return seatLabels;
	    }

	
//-------------Converting seats into List<seats> 
	  public List<Seats> formatSeats(List<String> seats) {
		  List<Seats> lst=new ArrayList<>();
		
		  for(String seatNme:seats) 
		  {
			  lst.add(new Seats(seatNme));
		  }
		  
		return lst;
}
	
}
