package org.edupoll.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.edupoll.app.command.AddSchedule;
import org.edupoll.app.data.MovieSchedule;
import org.edupoll.app.entity.Cinema;
import org.edupoll.app.entity.Movie;
import org.edupoll.app.entity.Schedule;
import org.edupoll.app.repository.CinemaRepository;
import org.edupoll.app.repository.MovieRepository;
import org.edupoll.app.repository.ScheduleRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
	private final ScheduleRepository scheduleRepository;
	private final MovieRepository movieRepository;
	private final CinemaRepository cinemaRepository;

	@GetMapping({ "/", "/all" })
	public String showSchedules(@RequestParam(required = false) LocalDate targetDate, Model model) {
		LocalDate today = LocalDate.now();

		List<LocalDate> period = new ArrayList<>();
		for (int gap = 0; gap < 7; gap++) {
			period.add(today.plusDays(gap));
		}
		model.addAttribute("period", period);

		if (targetDate == null) {
			targetDate = LocalDate.now();
		}
		model.addAttribute("targetDate", targetDate);
		
		
		Schedule sample = Schedule.builder().showDate(targetDate).build();

		
		List<Schedule> schedules = scheduleRepository.findAll(Example.of(sample), Sort.by("showDate").ascending());
		//스케줄에서 이 조건으로 하나 찾고.
		
		/*
		 * List<Movie> movies = new ArrayList<>(); for(Schedule s : schedules) {
		 * if(!movies.contains(s.getMovie())) { movies.add(s.getMovie()); } }
		 */
		List<Movie> movies = schedules.stream().map(t -> t.getMovie()).distinct().toList();
		
		
		List<MovieSchedule> movieSchedules = movies.stream().//
				map(t -> MovieSchedule.builder().movie(t)
						.schedules(schedules.stream().filter(e -> e.getMovie().equals(t)).toList()).build())
				.toList();

		/*
		 * schedules.stream().map(t -> t.getMovie()).distinct().map(t ->
		 * MovieSchedule.builder().movie(t) .schedules(schedules.stream().filter(e ->
		 * e.getMovie().equals(t)).toList()).build()).toList();
		 */

//		List<MovieSchedule> movieSchedules = new ArrayList<>();
//		for (Movie one : movies) {
//			List<Schedule> selected = new ArrayList<>();
//			for (Schedule t : schedules) {
//				if (t.getMovie().equals(one)) {
//					selected.add(t);
//				}
//			}
//			MovieSchedule done = //
//					MovieSchedule.builder().movie(one).schedules(selected).build();
//			movieSchedules.add(done);
//		}

		model.addAttribute("schedules", movieSchedules);
		return "schedule/schedule-all";
	}

	@GetMapping("/new")
	public String showAddSchedule(@RequestParam(required = false) Integer errorCode, Model model) {
		model.addAttribute("today", LocalDate.now().plusDays(1));
		model.addAttribute("movies", movieRepository.findAll());
		model.addAttribute("cinemas", cinemaRepository.findAll());
		model.addAttribute("errorCode", errorCode);
		return "schedule/schedule-new";
	}

	@PostMapping("/new")
	public String proceedAddSchedule(AddSchedule cmd) {
		Movie targetMovie = movieRepository.findById(cmd.getMovieId()).get();
		Cinema targetCinema = cinemaRepository.findById(cmd.getCinemaId()).get();
		LocalDate targetDate = LocalDate.parse(cmd.getShowDate());

		if (!targetDate.isAfter(LocalDate.now())) {
			return "redirect:/schedule/new?errorCode=1";
		}

		List<Schedule> registeredSchedules = scheduleRepository.findByCinemaAndShowDate(targetCinema, targetDate);
		// 등록하고자하는 시간대가 전부 가능한지 체크
		boolean creatable = true;
		flag: for (String time : cmd.getShowTime()) {
			if (time == null || time.equals(""))
				continue;

			LocalDateTime showTime = LocalDateTime.parse(cmd.getShowDate() + "T" + time);
			LocalDateTime closeTime = showTime.plusMinutes(targetMovie.getRunningTime());

			for (Schedule one : registeredSchedules) {
				LocalDateTime fixedShowTime = one.getShowTime();
				LocalDateTime fixedClosedTime = one.getClosedTime();
				if (!(closeTime.isBefore(fixedShowTime) || showTime.isAfter(fixedClosedTime))) {
					creatable = false;
					break flag;
				}
			}
		}
		if (!creatable) {
			return "redirect:/schedule/new?errorCode=2";
		}

		// =========================================================================
		for (String time : cmd.getShowTime()) {
			if (time == null || time.equals(""))
				continue;

			LocalDateTime showTime = LocalDateTime.parse(cmd.getShowDate() + "T" + time);
			Schedule entity = Schedule.builder().//
					cinema(targetCinema). //
					movie(targetMovie). //
					showDate(targetDate).//
					showTime(showTime).//
					closedTime(showTime.plusMinutes(targetMovie.getRunningTime())). //
					reservedCnt(0). //
					build();
			scheduleRepository.save(entity);
		}

		return "redirect:/schedule/new";
	}

	// AJAX 용
	@GetMapping("/api/cinema")
	@ResponseBody
	public List<Schedule> sendCinemaDetail(@RequestParam Integer cinemaId, @RequestParam String showDate) {
		Cinema targetCinema = cinemaRepository.findById(cinemaId).get();
		LocalDate targetDate = LocalDate.parse(showDate);
		List<Schedule> registeredSchedules = scheduleRepository.findByCinemaAndShowDate(targetCinema, targetDate);

		return registeredSchedules;
	}

	// AJAX 용
	@GetMapping("/api/movie")
	@ResponseBody
	public Movie sendMovieDetail(@RequestParam String movieId) {
		return movieRepository.findById(movieId).get();
	}

}
