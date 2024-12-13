package com.INOX.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.INOX.Entity.Seats;
import com.INOX.Repo.SeatsRepo;

@Service
public class SeatsService {

	@Autowired
	SeatsRepo seatrepo;
	
	public void delete(List<Seats> bookedSeats) {
		seatrepo.deleteAll(bookedSeats);
	}
	
	public Seats saveSeats(Seats seat) {
		return seatrepo.save(seat);
	}
	
	public void removebyid(Long id) {
		seatrepo.deleteById(id)	;
	}
}
