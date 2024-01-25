package org.edupoll.app.command;

import lombok.Data;

@Data

public class DeleteCinema {
	private String deleteCallName;
	private Integer deleteCapacity;
	private String deleteType;
	
	private DeleteCinemaMap deleteCinemaMap;
	
	
	

}
