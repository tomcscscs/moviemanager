package org.edupoll.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity

public class MovieTheater {
	@Id
	private Integer id;
	
	
	private String callName;
	private Integer capacity;
	
	/*
	 * //@Column(name= "theater_type") private String type;
	 */
	
	
	

}
