package com.cts.mhc.dao;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cts.mhc.exception.MovieHallException;
import com.cts.mhc.model.Movie;

public class MovieDAOIOImpl implements IMovieDAO {
	public static final String DATA_STORE_FILE_NAME = "bookstore.dat";

	private Map<String, Movie> movies;

	public MovieDAOIOImpl() throws MovieHallException {
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
					throw new MovieHallException("Not a valid DataStore");
				}
			} catch (IOException | ClassNotFoundException exp) {
				throw new MovieHallException("Loading Data Failed");
			}
		}
	}
	private IMovieDAO movieDao;

	public IMovieDAO getDAO(){
		return movieDao;
	}
	
	public boolean isValidName(String move_Hall){
		
		/*
		 * First letter must be capital
		 * Followed by three digits
		 */
		Pattern move_HallPattern = Pattern.compile("[A-Z]\\d{3}");
		Matcher move_HallMatcher = move_HallPattern.matcher(move_Hall);
		
		return move_HallMatcher.matches();
	}
	
	public boolean isValidMovie(String movie_name){
		/*
		 * First Letter should be capital
		 * Minimum length is 4 chars
		 * Maximum length is 20 chars.		
		 */
		Pattern movie_namePattern = Pattern.compile("[A-Z]\\w{3,19}");
		Matcher movie_nameMatcher = movie_namePattern.matcher(movie_name);
		
		return movie_nameMatcher.matches();
	}
	
	public boolean isValidticket(double ticket){
		/*
		 * ticket is between 5 and 5000
		 */
		return ticket>=5 && ticket<=5000;
	}
	
	public boolean isValidReleaseDate(LocalDate release_Date){
		/*
		 * publish date should not be greater than the current day.
		 */
		LocalDate today = LocalDate.now();
		//return release_Date.isBefore(today) || release_Date.equals(today);
		return today.isAfter(release_Date) || release_Date.equals(today);
	}
	
	public boolean isValidMovie(Movie Movie) throws MovieHallException{
		boolean isValid=false;
		
		List<String> errMsgs = new ArrayList<>();
		
		if(!isValidName(Movie.getName()))
			errMsgs.add("move_Hall should start with a capital alphabet followed by 3 digits");
		
		if(!isValidMovie(Movie.getMovie()))
			errMsgs.add("movie_name must start with capital and must be in between 4 to 20 chars in length");
		
		if(!isValidticket(Movie.getTicket()))
			errMsgs.add("ticket must be between INR.5 and INR.5000");
		
		if(!isValidReleaseDate(Movie.getDate()))
			errMsgs.add("Publish Date should not be a future date");
		
		if(errMsgs.size()==0)
			isValid=true;
		else
			throw new MovieHallException(errMsgs.toString());
		
		return isValid;
	}


	@Override
	public String add(Movie movie) throws MovieHallException {
		String movie_Hall=null;
		if(movie!=null && isValidMovie(movie)){
			movie_Hall=movieDao.add(movie);
		}
		return movie_Hall;
	}

	@Override
	public boolean delete(String move_Hall) throws MovieHallException {
		boolean isDone=false;
		if(move_Hall!=null && isValidName(move_Hall)){
			movieDao.delete(move_Hall);
			isDone = true;
		}else{
			throw new MovieHallException("move_Hall should be a capital letter followed by 3 digits");
		}
		return isDone;
	}

	@Override
	public Movie get(String move_Hall) throws MovieHallException {
		return movieDao.get(move_Hall);
	}

	@Override
	public List<Movie> getAll() throws MovieHallException {
		return movieDao.getAll();
	}

	@Override
	public boolean update(Movie Movie) throws MovieHallException {
		boolean isDone = false;
		
		if(Movie!=null && isValidMovie(Movie)){
			isDone = movieDao.update(Movie);
		}
		
		return isDone;
	}


	@Override
	public void persist() throws MovieHallException {
		try (ObjectOutputStream fout = new ObjectOutputStream(
				new FileOutputStream(DATA_STORE_FILE_NAME))) {
			fout.writeObject(movies);
		} catch (IOException exp) {
			throw new MovieHallException("Saving Data Failed");
		}
	}
}


