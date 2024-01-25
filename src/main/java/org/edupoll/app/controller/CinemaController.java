package org.edupoll.app.controller;

import java.util.List;

import org.edupoll.app.command.AddCinema;
import org.edupoll.app.command.ModifyCinema;
import org.edupoll.app.entity.Cinema;
import org.edupoll.app.entity.CinemaMap;
import org.edupoll.app.repository.CinemaMapRepository;
import org.edupoll.app.repository.CinemaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/all")
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
		Cinema found = cinemaRepository.findById(id).get();
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

		return "redirect:/cinema/" + id;
	}

	@GetMapping("/delete/{id}")
	public String proceedDelete(@PathVariable Integer id, Model model) {

		/*
		 * Cinema cinema = cinemaRepository.findById(id).get();
		 * cinema.setCallName(dcd.getDeleteCallName());
		 * cinema.setCapacity(dcd.getDeleteCapacity());
		 * cinema.setType(dcd.getDeleteType());
		 * 
		 * if (cinema.getCinemaMap() == null) { CinemaMap cinemaMap =
		 * CinemaMap.builder().line(dcd.getDeleteCinemaMap().getDeleteLine()) //
		 * .seat(dcd.getDeleteCinemaMap().getDeleteSeat()).build();
		 * cinemaMapRepository.save(cinemaMap); cinema.setCinemaMap(cinemaMap);
		 * 
		 * } else { CinemaMap cinemaMap = cinema.getCinemaMap();
		 * cinemaMap.setLine(dcd.getDeleteCinemaMap().getDeleteLine());
		 * cinemaMap.setSeat(dcd.getDeleteCinemaMap().getDeleteSeat());
		 * cinemaMapRepository.save(cinemaMap); }
		 */

		cinemaRepository.deleteById(id);
		cinemaMapRepository.deleteById(id);
		
		

		return "redirect:/cinema/all";

		/*
		 * Optional<Feed> optional = feedRepository.findById(cmd.getFeedId()); if
		 * (optional.isEmpty()) { return "community/error"; }
		 * 
		 * Feed feed = optional.get(); boolean role =
		 * feed.getPassword().equals(cmd.getFeedPassword()); if (!role) {
		 * 
		 * return "redirect:/community/delete?id=" + cmd.getFeedId(); } //
		 * feedRepository.deleteById(cmd.getFeedId()); feedRepository.delete(feed);
		 */

	}
}
