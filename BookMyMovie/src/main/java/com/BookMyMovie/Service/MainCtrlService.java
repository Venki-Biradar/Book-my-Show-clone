package com.BookMyMovie.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.BookMyMovie.ModelDB.modelDB;
import com.BookMyMovie.entity.Movie;
import com.BookMyMovie.entity.Seats;
import com.BookMyMovie.entity.Theaters;

@Service
public class MainCtrlService {

	/*-----------------------------External Api Interaction ----------------------------------------------*/

	
	@Autowired
	RestTemplate resttemplate;
	public ResponseEntity<String> getINOXdata() {         // data taking from Both Inox and PVR api 
		String URL="http://localhost:8080/INOX/theaters/all";	
	
		ResponseEntity<Theaters[]> INOXdata=resttemplate.getForEntity(URL, Theaters[].class);
		List<Theaters> InoxData=new ArrayList<Theaters>( Arrays.asList(INOXdata.getBody()));
		
		//System.out.println(InoxData.size());
		
		String URL_PVR="http://localhost:8081/PVR/theaters/all";		
		ResponseEntity<Theaters[]> PVRdata=resttemplate.getForEntity(URL_PVR, Theaters[].class);
		List<Theaters> PvrData=new ArrayList<Theaters>( Arrays.asList(PVRdata.getBody()));
		
		//System.out.println(PvrData.size());
		
		InoxData.addAll(PvrData);
		//System.out.println(InoxData.size());
		
		modelDB.addTheaters(InoxData);
		
		ResponseEntity<String> res=ResponseEntity.ok("Record Fetched");
		return res;
	}

	
	//sending Seats data into particular API Service
	

	public void SendDataOntheBasisOfTheatorOrigin(String origin,Long theaterId,Long ScreenID ,List <Seats> lst) {

		if(origin.equalsIgnoreCase("INOX")) 
		{
			String url="http://localhost:8080/INOX/theaters/bookTicket?Theaterid="+theaterId+"&ScreenID="+ScreenID;
			System.out.println( resttemplate.postForObject(url, lst, String.class) );
		}
		else 
		{
			String url="http://localhost:8081/PVR/theaters/bookTicket?Theaterid="+theaterId+"&ScreenID="+ScreenID;
			System.out.println( resttemplate.postForObject(url, lst, String.class) );
			
		}
		
	}
	
	
	/*------------------------------------------------------------------------------------------------------*/
	/*------------------------------Internal Data Model Interaction-----------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/
	
	
	public List<Movie> getMoviesOfSpecificLocation(String location)
	{
		return modelDB.FilterBasedOnlocation(location);
	}
	
}
