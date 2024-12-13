package com.BookMyMovie.ModelDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.BookMyMovie.entity.Movie;
import com.BookMyMovie.entity.Screens;
import com.BookMyMovie.entity.Theaters;

public class modelDB {

	static List<Theaters> InoxData = new ArrayList<>();

	public static void addTheaters(List<Theaters> lst) {
		InoxData.clear();
		InoxData.addAll(lst);

	}

	public static List<Movie> FilterBasedOnlocation(String Location) {
		List<Theaters> theaters;
		if (Location.equalsIgnoreCase("all")) {
			theaters = InoxData;
		} else {
			theaters = InoxData.stream().filter(a -> a.getLocation().equalsIgnoreCase(Location))
					.collect(Collectors.toList());
		}
		Map<Long, Movie> uniqueMoviesMap = theaters.stream().flatMap(theater -> theater.getScreens().stream())
				.map(Screens::getMovieRef)
				.collect(Collectors.toMap(Movie::getMuid, movie -> movie, (existing, replacement) -> existing));

		return uniqueMoviesMap.values().stream().collect(Collectors.toList());

	}

	public static List<Theaters> getTheatersByMovieNameAndLocation(String movieName, String location) {

		List<Theaters> availabletheaters = InoxData.stream()
				.filter(theater -> theater.getLocation().equalsIgnoreCase(location))
				.filter(theater -> theater.getScreens().stream()
						.anyMatch(screen -> screen.getMovieRef().getMovieName().equalsIgnoreCase(movieName)))
				.collect(Collectors.toList());
		return availabletheaters;
	}

	public static List<String> getSoldTicketsbyTheaterIdandScreenId(String origin,Long theaterId, Long screenId) {
		
		List<String> soldSeats=InoxData.stream().filter(theater -> theater.getTheaterId().equals(theaterId)&&theater.getOrigin().equals(origin))
				.flatMap(theater -> theater.getScreens().stream())
				.filter(screen -> screen.getScreenId().equals(screenId)).
				flatMap(screen -> screen.getSeats().stream()).map(seat->seat.getSeat_name()).toList();
		
		return soldSeats;
	}

	static public List<Theaters> getInoxdata() {
		return InoxData;
	}

	public static Theaters GetTheaterByIdandOrgin(Long theaterId, String origin) {
		
		return
		InoxData.stream().
		filter(theater->theater.getOrigin().equalsIgnoreCase(origin) &&theater.getTheaterId()==theaterId)
		.findFirst().get();
	}

}
