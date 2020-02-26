package com.cts.mhc.dao;

import com.cts.mhc.model.Movie;
import com.cts.mhc.exception.MovieHallException;

import java.util.List;

public interface IMovieDAO {	
	String add(Movie movie) throws MovieHallException;
	boolean delete(String movie_Hall)throws MovieHallException;
	Movie get(String hall_Name) throws MovieHallException;;
	List<Movie> getAll() throws MovieHallException;;
	boolean update(Movie movie) throws MovieHallException;
	void persist()throws MovieHallException;
}
