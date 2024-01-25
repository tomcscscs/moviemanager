package org.edupoll.app.command;

import lombok.Data;

@Data
public class AddNewCinema {
	private String callName;
	private String type;
	private Integer capacity;
}
