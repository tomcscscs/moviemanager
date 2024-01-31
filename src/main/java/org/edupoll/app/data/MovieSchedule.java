package org.edupoll.app.data;

import java.util.List;

import org.edupoll.app.entity.Movie;
import org.edupoll.app.entity.Schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSchedule {
	private Movie movie;
	private List<Schedule> schedules;
}
