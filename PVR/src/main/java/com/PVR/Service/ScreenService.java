package com.PVR.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PVR.Entity.Screens;
import com.PVR.Repo.ScreenRepo;

@Service
public class ScreenService {

	@Autowired
	ScreenRepo screpo;
	
	public Screens saveScreen(Screens screen) {
		return screpo.save(screen);
	}
	public Screens getbyId(Long id) {
		// TODO Auto-generated method stub
		return screpo.findById(id).get();
		 
	}
}
