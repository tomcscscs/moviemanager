package org.edupoll.app.command;

import java.util.List;

import lombok.Data;

@Data
public class AddSchedule {

	private Integer cinemaId;
	private String movieId;
	private String showDate;
	private List<String> showTime;
	
}
