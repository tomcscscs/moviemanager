package org.edupoll.app.repository;

import org.edupoll.app.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

}
