package org.edupoll.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name="cinema_maps")


public class CinemaMap {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;

	
	private Integer line;
	private Integer seat;
	
	
	
	

}
