package com.PVR.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PVR.Entity.Screens;
@Repository
public interface ScreenRepo extends JpaRepository<Screens, Long>{

}
