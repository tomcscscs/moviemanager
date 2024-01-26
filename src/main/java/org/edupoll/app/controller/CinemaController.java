package org.edupoll.app.controller;

import java.util.List;
import java.util.Optional;

import org.edupoll.app.command.AddCinema;
import org.edupoll.app.command.ModifyCinema;
import org.edupoll.app.entity.Cinema;
import org.edupoll.app.entity.CinemaMap;
import org.edupoll.app.repository.CinemaMapRepository;
import org.edupoll.app.repository.CinemaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cinema")
public class CinemaController {

	private final CinemaRepository cinemaRepository;
	private final CinemaMapRepository cinemaMapRepository;

	@GetMapping({"","/all"})
	public String showAddCinemaForm(Model model) {
		List<Cinema> list = cinemaRepository.findAll();
		model.addAttribute("cinemas", list);
		return "cinema/all";
	}

	@PostMapping("/new")
	public String proceedAddCinema(AddCinema cmd) {
		Cinema cinema = Cinema.builder().//
				callName(cmd.getCallName()).//
				capacity(cmd.getCapacity()).//
				type(cmd.getType()).//
				build();

		cinemaRepository.save(cinema);

		return "redirect:/cinema/all";
	}

	@GetMapping("/{id}")
	public String showCinemaDetail(@PathVariable Integer id, Model model) {
		Optional<Cinema> optional = cinemaRepository.findById(id);
		if(optional.isEmpty()) {
			 return "redirect:/cinema/all";
		}
		Cinema found = optional.get();
		// System.out.println(found.getCinemaMap());
		model.addAttribute("found", found);
		return "cinema/detail";
	}

	@PutMapping("/{id}")
	public String proceedModifyCinema(@PathVariable Integer id, @ModelAttribute ModifyCinema cmd, Model model) {
		// 시네마도 수정, 시네마맵도 수정
		Cinema cinema = cinemaRepository.findById(id).get();
		cinema.setCallName(cmd.getNewCallName());
		cinema.setCapacity(cmd.getNewCapacity());
		cinema.setType(cmd.getNewType());

		if (cinema.getCinemaMap() == null) {
			CinemaMap cinemaMap = CinemaMap.builder().line(cmd.getCinemaMap().getNewLine()) //
					.seat(cmd.getCinemaMap().getNewSeat()).build();
			cinemaMapRepository.save(cinemaMap);
			cinema.setCinemaMap(cinemaMap);
		} else {
			CinemaMap cinemaMap = cinema.getCinemaMap();
			cinemaMap.setLine(cmd.getCinemaMap().getNewLine());
			cinemaMap.setSeat(cmd.getCinemaMap().getNewSeat());
			cinemaMapRepository.save(cinemaMap);
		}

		cinemaRepository.save(cinema);

		return "redirect:/cinema/"+id;
	}
	
	
	@DeleteMapping("/{id}")
	public String proceedDeleteCinema(@PathVariable Integer id) {
		Optional<Cinema> optional = cinemaRepository.findById(id);
		if(optional.isEmpty()) {
			 return "redirect:/cinema/all";
		}
		Cinema cinema = optional.get();
		cinemaRepository.delete(cinema);
		if(cinema.getCinemaMap() != null) {
			cinemaMapRepository.delete(cinema.getCinemaMap());
		}
		return "redirect:/cinema/all";
	}
}














