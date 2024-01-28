package org.edupoll.app.command;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AddMovie {
	private String movieTitle;
	private LocalDate movieReleasedAt;
	private Integer movieRunningTime;
	private Integer movieAgeCut;
	private Integer movieGenreId;
	
}
