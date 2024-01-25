package org.edupoll.app.controller;

import java.util.List;

import org.edupoll.app.command.AddNewCinema;
import org.edupoll.app.entity.Cinema;
import org.edupoll.app.repository.CinemaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cinema")
public class CinemaController {

	private final CinemaRepository cinemaRepository;

	@GetMapping("/all")
	public String showAddCinemaForm(Model model) {
		List<Cinema> list =cinemaRepository.findAll();
		model.addAttribute("cinemas", list);
		return "cinema/all";
	}

	@PostMapping("/new")
	public String proceedAddCinema( AddNewCinema cmd) {
		Cinema cinema = Cinema.builder().//
				callName(cmd.getCallName()).//
				capacity(cmd.getCapacity()).//
				type(cmd.getType()).//
				build();
		
		cinemaRepository.save(cinema);

		return "redirect:/cinema/all";
	}
}
