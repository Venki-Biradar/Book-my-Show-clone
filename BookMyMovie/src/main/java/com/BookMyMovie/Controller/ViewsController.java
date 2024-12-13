package com.BookMyMovie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.BookMyMovie.Service.MainCtrlService;
import com.BookMyMovie.Service.VIewService;
import com.BookMyMovie.entity.Movie;
import com.BookMyMovie.entity.Screens;
import com.BookMyMovie.entity.Seats;
import com.BookMyMovie.entity.Theaters;

@Controller
public class ViewsController {

	@Autowired
	VIewService viewService;

	@GetMapping("/home")
	public String homePage(Model model) {
		maincontrollerservice.getAllDataOfINOX(); // getting and storing all data from PVR and INOX api 
		List<Movie> movies = viewService.filtermoviesbasedOnlocation("all");
		model.addAttribute("movies", movies);
		return "homepage";
	}

	@GetMapping("/filterMovies")
	public String filterMovies(@RequestParam String location, Model model) {
		List<Movie> movies = viewService.filtermoviesbasedOnlocation(location);
		;
		model.addAttribute("movies", movies);
		return "homepage";
	}

	@GetMapping("/bookMovie")
	public String bookMovie(@RequestParam String movieName, @RequestParam String location, Model model) {
		List<Theaters> theaters = viewService.getTheatersByName_and_location(movieName, location);
		model.addAttribute("theaters", theaters);
		model.addAttribute("movieName", movieName);
		model.addAttribute("location", location);

		return "bookMovie";
	}

	/*
	 * @GetMapping("/bookTicket") public String bookTicket(@RequestParam Long
	 * theaterId, @RequestParam Long screenId, @RequestParam String movieName,
	 * 
	 * @RequestParam String location, Model model) { List<String> Soldseats =
	 * List.of("A1", "A2", "B2"); // Example of sold seats
	 * model.addAttribute("theaterId", theaterId); model.addAttribute("screenId",
	 * screenId); model.addAttribute("movieName", movieName);
	 * model.addAttribute("location", location); model.addAttribute("seatNos",
	 * Soldseats); return "bookTicket"; }
	 */

	@Autowired
	MainController maincontrollerservice;

	@GetMapping("/bookTicket")
	public String bookTicket(@RequestParam Long theaterId, @RequestParam Long screenId, @RequestParam String movieName,
			@RequestParam String location, @RequestParam String origin, Model model) {

		// List<String> Soldseats = List.of("A1", "A2", "B2"); // eg seats
		maincontrollerservice.getAllDataOfINOX();

		List<String> Soldseats = viewService.getsoldseatsnameByScreenIdAndTheaterId(origin,theaterId, screenId);
		System.out.println(Soldseats);
		List<String> seatLabels = viewService.generateSeatLabels();
		model.addAttribute("theaterId", theaterId);
		model.addAttribute("screenId", screenId);
		model.addAttribute("movieName", movieName);
		model.addAttribute("location", location);
		model.addAttribute("origin", origin);
		model.addAttribute("soldSeats", Soldseats);
		model.addAttribute("seatLabels", seatLabels);

		return "bookTicket";
	}

	@GetMapping("/bookTicketConfirm")
	public String bookTicketConfirm(@RequestParam Long theaterId, @RequestParam Long screenId,
			@RequestParam String movieName, @RequestParam String location, @RequestParam String origin,
			@RequestParam List<String> seats, Model model) {
		model.addAttribute("origin", origin);
		model.addAttribute("theaterId", theaterId);
		model.addAttribute("screenId", screenId);

		Theaters Theater = viewService.getTheaterByIdandOrgin(theaterId, origin);
		Screens screen = Theater.getScreens().stream().filter(s -> s.getScreenId() == screenId).findFirst().get();
		model.addAttribute("MyTheater", Theater);
		model.addAttribute("MyScreen", screen);

		model.addAttribute("movieName", movieName);
		model.addAttribute("location", location);
		model.addAttribute("seats", seats);
		return "bookTicketConfirm";
	}

	// ----------------

	
	@Autowired
	MainCtrlService mainControllerService;
	@GetMapping("/completeBooking")
	public String completeBooking(@RequestParam Long theaterId, @RequestParam Long screenId,
			@RequestParam String movieName, @RequestParam String location, @RequestParam String origin,
			@RequestParam List<String> seats, @RequestParam String customerName, @RequestParam String email,
			Model model) {
		model.addAttribute("theaterId", theaterId);
		model.addAttribute("screenId", screenId);
		model.addAttribute("movieName", movieName);
		model.addAttribute("location", location);
		model.addAttribute("origin", origin);
		model.addAttribute("seats", seats);
		model.addAttribute("customerName", customerName);
		model.addAttribute("email", email); 
		
		
		
		Theaters Theater = viewService.getTheaterByIdandOrgin(theaterId, origin);
		Screens screen = Theater.getScreens().stream().filter(s -> s.getScreenId() == screenId).findFirst().get();
		model.addAttribute("MyTheater", Theater);
		model.addAttribute("MyScreen", screen);
		
		//final stage
		List<Seats> st=viewService.formatSeats(seats);
		mainControllerService.SendDataOntheBasisOfTheatorOrigin(origin, theaterId, screenId, st);
		
		return "bookingConfirmation";
		}
	

}