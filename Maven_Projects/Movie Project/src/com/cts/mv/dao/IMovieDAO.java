package com.cts.mv.dao;

import java.time.LocalDate;
import java.util.List;

import com.cts.mv.exception.MovieException;
import com.cts.mv.model.Movie;

public interface IMovieDAO {
	String add(Movie movie) throws MovieException;
	boolean delete(String mvId)throws MovieException;
	Movie searchId(String mvId) throws MovieException;
	Movie searchName(String mvName) throws MovieException;
	Movie searchHero(String mvHero) throws MovieException;
	Movie searchDate(LocalDate releaseDate) throws MovieException;
	Movie searchCollection(Double collection) throws MovieException;
	List<Movie> getAll() throws MovieException;;
	boolean update(Movie movie) throws MovieException;
	void persist()throws MovieException;
}
