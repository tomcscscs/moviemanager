package org.edupoll.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Entity
@Table(name="cinemas")
public class Cinema {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
 
	private String callName;
	private Integer capacity;
	
//	@Column(name = "theater_type")
	private String type;
	
	
	@OneToOne
	private CinemaMap cinemaMap;
	

}
