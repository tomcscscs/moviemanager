package org.edupoll.app.command;

import java.time.LocalDateTime;

import lombok.Data;

@Data

public class AddSchedule {
	
	private Integer cinemaId;
	private Integer movieId;
	private LocalDateTime showTime;
	
	
	

}
