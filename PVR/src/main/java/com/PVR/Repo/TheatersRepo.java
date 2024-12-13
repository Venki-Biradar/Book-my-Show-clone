package com.PVR.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PVR.Entity.Theaters;
@Repository
public interface TheatersRepo extends JpaRepository<Theaters, Long>{

}
