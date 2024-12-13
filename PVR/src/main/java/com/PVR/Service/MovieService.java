package com.PVR.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.PVR.Entity.Movie;
import com.PVR.Repo.MovieRepo;
@Service
public class MovieService {

	@Autowired
	MovieRepo Mrepo;

	public List<Movie> getall() {
		return Mrepo.findAll();
	}
	
	public Movie getmoviebyname(String mname) {
		return Mrepo.findByMovieName(mname);
	}

	public Movie saveOrUpdate(Movie movie) {
		return Mrepo.save(movie);
	}

	

}
