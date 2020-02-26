package com.cts.mhc.service;

import com.cts.mhc.model.Movie;
import com.cts.mhc.exception.MovieHallException;

import java.util.List;

public interface IMovieService {	
	String add(Movie movie) throws MovieHallException;
	boolean delete(String movie_hall) throws MovieHallException;
	Movie get(String movie_hall) throws MovieHallException;
	List<Movie> getAll() throws MovieHallException;;
	boolean update(Movie movie) throws MovieHallException;
	void persist()throws MovieHallException;
}
