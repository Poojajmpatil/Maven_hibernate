package com.cts.mv.service;

import java.time.LocalDate;
import java.util.List;

import com.cts.mv.exception.MovieException;
import com.cts.mv.model.Movie;

public interface IMovieService {
	String add(Movie movie) throws MovieException;
	boolean delete(String mvId) throws MovieException;
	Movie searchMvId(String mvId) throws MovieException;
	Movie searchMvName(String mvName) throws MovieException;
	Movie searchMvHero(String mvHero) throws MovieException;
	Movie searchRDate(LocalDate releaseDate) throws MovieException;
	Movie searchCollection(Double collection) throws MovieException;
	List<Movie> getAll() throws MovieException;;
	boolean update(Movie movie) throws MovieException;
	void persist()throws MovieException;
}
