package com.PVR.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PVR.Entity.Movie;
@Repository
public interface MovieRepo extends JpaRepository<Movie, Long>{

	Movie findByMovieName(String movieName);

}
