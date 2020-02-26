package com.cts.mhc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cts.mhc.model.Movie;
import com.cts.mhc.exception.MovieHallException;

public class MovieDAOCollectionImpl implements IMovieDAO {
	
	private Map<String, Movie> movies;
	
	public MovieDAOCollectionImpl() {
		movies = new TreeMap<>();
	}
	
	@Override
	public String add(Movie movie) throws MovieHallException {
		String movie_Name = null;
		if (movie != null) {
			movie_Name = movie.getName();
			movies.put(movie_Name, movie);	
		}
		return movie_Name;
	}
	
	@Override
	public boolean delete(String movie_Hall) throws MovieHallException {
		boolean isDone = false;
		if (movie_Hall != null) {
			movies.remove(movie_Hall);
			isDone = true;
		}
		return isDone;
	}
	
	@Override
	public Movie get(String movie_Name) throws MovieHallException {
		return movies.get(movie_Name);
	}
	

	@Override
	public List<Movie> getAll() throws MovieHallException {
		return new ArrayList<>(movies.values());
	}
	
	@Override
	public boolean update(Movie Movie) throws MovieHallException {
		boolean isDone = false;
		if (Movie != null) {
			String movie_Name = Movie.getMovie();
			movies.replace(movie_Name, Movie);
			
		}
		return isDone;
	}

	@Override
	public void persist() throws MovieHallException {
		/* no implementation is required */
	}
	
	
}
