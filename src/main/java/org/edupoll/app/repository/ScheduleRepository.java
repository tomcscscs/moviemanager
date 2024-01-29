package org.edupoll.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.edupoll.app.entity.Cinema;
import org.edupoll.app.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	public List<Schedule> findByCinemaAndShowDate(Cinema cinema, LocalDate showdate);
}
