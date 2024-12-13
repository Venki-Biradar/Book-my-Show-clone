package com.INOX.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.INOX.Entity.Screens;
@Repository
public interface ScreenRepo extends JpaRepository<Screens, Long>{

}
