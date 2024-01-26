package org.edupoll.app.repository;

import java.util.List;

import org.edupoll.app.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
	
	public List<Movie> findByGenreId(Integer genreId);
	
	

}
