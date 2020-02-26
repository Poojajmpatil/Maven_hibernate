package com.cts.mv.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cts.mv.exception.MovieException;
import com.cts.mv.model.Movie;

public class MovieDAOIOStreamImpl implements IMovieDAO {
	public static final String DATA_STORE_FILE_NAME = "movies.dat";

	private Map<String, Movie> movies;

	@SuppressWarnings("unchecked")
	public MovieDAOIOStreamImpl() throws MovieException {
		File file = new File(DATA_STORE_FILE_NAME);

		if (!file.exists()) {
			movies = new TreeMap<>();
		} else {
			try (ObjectInputStream fin = new ObjectInputStream(
					new FileInputStream(DATA_STORE_FILE_NAME))) {

				Object obj = fin.readObject();

				if (obj instanceof Map) {
					movies = (Map<String, Movie>) obj;
				} else {
					throw new MovieException("Not a valid DataStore");
				}
			} catch (IOException | ClassNotFoundException exp) {
				throw new MovieException("Loading Data Failed");
			}
		}
	}
	@Override
	public String add(Movie movie) throws MovieException {
		String mvId = null;
		if (movie != null) {
			mvId = movie.getMvId();
			movies.put(mvId, movie);
		}
		return mvId;
	}

	@Override
	public boolean delete(String mvId) throws MovieException {
		boolean isDone = false;
		if (mvId != null) {
			movies.remove(mvId);
			isDone = true;
		}
		return isDone;
	}

	@Override
	public Movie searchId(String mvId) throws MovieException {
		return movies.get(mvId);
	}

	@Override
	public Movie searchName(String mvName) throws MovieException {
		return movies.get(mvName);
	}

	@Override
	public Movie searchHero(String mvHero) throws MovieException {
		return movies.get(mvHero);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Movie searchDate(LocalDate releaseDate) throws MovieException {
		return movies.get(releaseDate);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Movie searchCollection(Double collection) throws MovieException {
		return movies.get(collection);
	}

	@Override
	public List<Movie> getAll() throws MovieException {
		return new ArrayList<Movie>(movies.values());
	}

	@Override
	public boolean update(Movie movie) throws MovieException {
		boolean isDone = false;
		if (movie != null) {
			String mvId = movie.getMvId();
			movies.replace(mvId, movie);
		}
		return isDone;
	}

	@Override
	public void persist() throws MovieException {
		try (ObjectOutputStream fout = new ObjectOutputStream(
				new FileOutputStream(DATA_STORE_FILE_NAME))) {
			fout.writeObject(movies);
		} catch (IOException exp) {
			throw new MovieException("Saving Data Failed");
		}
	}

}
