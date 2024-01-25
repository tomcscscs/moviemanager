package org.edupoll.app.controller;

import org.edupoll.app.command.newCommand;
import org.edupoll.app.entity.Cinema;
import org.edupoll.app.repository.CinemaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cinema")
@RequiredArgsConstructor
public class CinemaController {

	private final CinemaRepository cinemaRepository;

	@GetMapping("/new")
	public String showAddCinemaForm() {
		return "cinema/new";

	}

	@PostMapping("/new")
	public String proceedAddCinema(@ModelAttribute newCommand ncd, Model model) {
		Cinema cinema = Cinema.builder().//
				id(ncd.getNewId()).//
				callName(ncd.getNewName()).//
				capacity(ncd.getNewCapacity()).//
				type(ncd.getNewType()).//
				build();
		
		Cinema one = cinemaRepository.save(cinema);
	
		model.addAttribute("cinemaSave", one);
		
		
		
		
		return "redirect:/cisnema/new";
		


	}

}
