package org.edupoll.app.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ModifyCinema {
	private String newCallName;
	private String newType;
	private Integer newCapacity;
	
	
	private ModifyCinemaMap cinemaMap;
	
	
}
