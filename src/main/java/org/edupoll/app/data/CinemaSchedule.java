package org.edupoll.app.data;

import java.util.List;

import org.edupoll.app.entity.Cinema;
import org.edupoll.app.entity.Schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CinemaSchedule {

	private Cinema cinema;
	private List<Schedule> schedules;

}
