package org.edupoll.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.edupoll.app.entity.Genre;
import org.edupoll.app.repository.CinemaRepository;
import org.edupoll.app.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor

public class ScheduleController {
	
	private final MovieRepository movieRepository;
	private final CinemaRepository cinemaRepository;
	
	

	@GetMapping("/all")
	public String showScheduleAllPage(Model model) {
		List<LocalDate> periods = new ArrayList<>();

		LocalDate start = LocalDate.now();

		for (int i = 0; i <= 6; i++) {
			periods.add(start.plusDays(i));
		}

		model.addAttribute("periods", periods);

		return "schedule/all";

	}

	@GetMapping("/register")
	public String showScheduleInputForRegister(Model model) {
		
		model.addAttribute("movies", movieRepository.findAll());
		
		 LocalDate startDate = LocalDate.of(1950, 1, 1);
		    LocalDate endDate = LocalDate.now();
		    
		    List<LocalDate> dateList = new ArrayList<>();
		    LocalDate currentDate = startDate;

		    while (!currentDate.isAfter(endDate)) {
		        dateList.add(currentDate);
		        currentDate = currentDate.plusDays(1);
		    }

		    model.addAttribute("dateList", dateList);
		    
		    model.addAttribute("cinemas",cinemaRepository.findAll());

		    
		

		return "schedule/register";

	}

}
