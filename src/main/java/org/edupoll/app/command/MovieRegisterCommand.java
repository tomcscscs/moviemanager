package org.edupoll.app.command;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MovieRegisterCommand {
	private String movieTitle;
	private LocalDate movieReleasedAt;
	private Integer runningTime;
	private Integer movieGenreId;
	private Integer movieAgeCut;

}
