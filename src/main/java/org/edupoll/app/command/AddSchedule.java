package org.edupoll.app.command;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AddSchedule {

	private Integer cinemaId;
	private String movieId;
	private String showDate;
	private String showTime;
	
}
