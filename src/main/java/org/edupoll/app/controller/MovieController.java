package org.edupoll.app.controller;

import java.util.List;

import org.edupoll.app.command.MovieRegisterCommand;
import org.edupoll.app.entity.Genre;
import org.edupoll.app.entity.Movie;
import org.edupoll.app.repository.GenreRepository;
import org.edupoll.app.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/movie")

public class MovieController {
	private final MovieRepository movieRepository;
	private final GenreRepository genreRepository;

	@GetMapping("/all")
	public String movieDataInputForm(Model model) {
		
		List<Movie> movies = movieRepository.findAll();
		List<Genre> genres = genreRepository.findAll();
		
		model.addAttribute("movies", movies);
		model.addAttribute("genres", genres);
		

		return "cinema/movieAll";

	}

	@PostMapping("/new")
	public String movieDataProcessingForm(@ModelAttribute MovieRegisterCommand mgd, Model model) {
		Movie one = Movie.builder().title(mgd.getMovieTitle()).releasedAt(mgd.getMovieReleasedAt()).//
				runningTime(mgd.getRunningTime()).//
				genre(genreRepository.findById(mgd.getMovieGenreId()).get()).//
				ageCut(mgd.getMovieGenreId()).build();

		movieRepository.save(one);

		return "redirect:/movie/all";

	}

}
