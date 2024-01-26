package org.edupoll.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="schedules")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cinema cinema;
	
	@ManyToOne
	private Movie movie;
	
	private LocalDateTime showtime;
	
	private Integer reservedCnt;
	
	@PrePersist
	public void prePersist() {
		//최초 세이브가 될때. 디폴트 벨류드를 설정해줄 수 있습니다. 
		
		this.reservedCnt=0;
		
	}
	
	

}
