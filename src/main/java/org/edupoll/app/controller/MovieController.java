package org.edupoll.app.controller;

import java.util.List;

import org.edupoll.app.command.AddMovie;
import org.edupoll.app.entity.Genre;
import org.edupoll.app.entity.Movie;
import org.edupoll.app.repository.GenreRepository;
import org.edupoll.app.repository.MovieRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

	private final MovieRepository movieRepository;
	private final GenreRepository genreRepository;

	@GetMapping({ "", "/all" })
	public String showManageView(@RequestParam(required = false) Integer genreId, Model model) {

		model.addAttribute("currentGenreId", genreId);

		List<Genre> genres = genreRepository.findAll();
		model.addAttribute("genres", genres);

		
		if (genreId == null) {
			model.addAttribute("movies", movieRepository.findAll());
		} else {
//			Genre genre = genreRepository.findById(genreId).get();
//			
//			model.addAttribute("movies", movieRepository.findByGenre(genre));
			model.addAttribute("movies", movieRepository.findByGenreId(genreId));
		}
		
		
// 		Exmaple 을 사용
//		if (genreId == null) {
//			model.addAttribute("movies", movieRepository.findAll());
//		} else {
//			Genre genre = genreRepository.findById(genreId).get();
//			Movie sample = Movie.builder().genre(genre).build();
//			model.addAttribute("movies", movieRepository.findAll(Example.of(sample)));
//		}

		/* List로 수동으로 서치 */
//		List<Movie> movies = movieRepository.findAll();
//		if (genreId == null) {
//			model.addAttribute("movies", movies);
//		} else {
//			List<Movie> filteredMovie = new ArrayList<>();
//			for (Movie one : movies) {
//				if (one.getGenre().getId() == genreId) {
//					filteredMovie.add(one);
//				}
//			}
//			model.addAttribute("movies", filteredMovie);
//		}

		return "movie/all";
	}

	@PostMapping("/new")
	public String proceedAddNewMovie(AddMovie cmd) {

		Movie entity = Movie.builder().//
				ageCut(cmd.getMovieAgeCut()).//
				genre(genreRepository.findById(cmd.getMovieGenreId()).get()).//
				releasedAt(cmd.getMovieReleasedAt()).//
				runningTime(cmd.getMovieRunningTime()).//
				title(cmd.getMovieTitle()).build();
		movieRepository.save(entity);

		return "redirect:/movie/all";
	}
}
