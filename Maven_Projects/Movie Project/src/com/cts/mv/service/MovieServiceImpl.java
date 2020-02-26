package com.cts.mv.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cts.mv.dao.IMovieDAO;
import com.cts.mv.dao.MovieDAOIOStreamImpl;
import com.cts.mv.dao.MovieDAOJDBCImpl;
import com.cts.mv.exception.MovieException;
import com.cts.mv.model.Movie;

public class MovieServiceImpl implements IMovieService {
	private IMovieDAO movieDao;

	public IMovieDAO getDAO(){
		return movieDao;
	}
	
	public MovieServiceImpl() throws MovieException {
		movieDao = new MovieDAOJDBCImpl();
		//movieDao = new MovieDAOIOStreamImpl();
		
	}
	
	public boolean isValidMvId(String mvId){
		
		/*
		 * First letter must be capital
		 * Followed by three digits
		 */
		Pattern mvIdPattern = Pattern.compile("[A-Z]\\d{3}");
		Matcher mvidMatcher = mvIdPattern.matcher(mvId);
		
		return mvidMatcher.matches();
	}
	
	public boolean isValidTitle(String mvName){
		
		
		/*
		 * First Letter should be capital
		 * Minimum length is 4 chars
		 * Maximum length is 20 chars.		
		 */
		Pattern titlePattern = Pattern.compile("[A-Z]\\w{3,19}");
		Matcher titleMatcher = titlePattern.matcher(mvName);
		
		return titleMatcher.matches();
	}
	
	public boolean isValidHero(String mvHero){
		/*
		 * First Letter should be capital
		 * Minimum length is 4 chars
		 * Maximum length is 20 chars.		
		 */
		Pattern HeroPattern = Pattern.compile("[A-Z]\\w{3,19}");
		Matcher HeroMatcher = HeroPattern.matcher(mvHero);
		
		return HeroMatcher.matches();
	}
	
	public boolean isValidPrice(double collection){
		/*
		 * collection is between 5000 and 500000
		 */
		return collection>=5000 && collection<=500000;
	}
	
	public boolean isValidReleaseDate(LocalDate releaseDate){
		/*
		 * publish date should not be greater than the current day.
		 */
		LocalDate today = LocalDate.now();
		//return publishDate.isBefore(today) || publishDate.equals(today);
		return today.isAfter(releaseDate) || releaseDate.equals(today);
	}
	
	public boolean isValidMovie(Movie movie) throws MovieException{
		boolean isValid=false;
		
		List<String> errMsgs = new ArrayList<>();
		
		
		if(!isValidMvId(movie.getMvId())) {
			errMsgs.add("MovieId should start with a capital alphabet followed by 3 digits");
		}
		
		if(!isValidTitle(movie.getMvName()))
			errMsgs.add("Movie must start with capital and must be in between 4 to 20 chars in length");
		
		if(!isValidHero(movie.getMvHero()))
			errMsgs.add("MovieHero name should start with a capital alphabet followed by 3 digits");
		
		if(!isValidPrice(movie.getCollection()))
			errMsgs.add("Collection must be between INR.5 and INR.5000");
		
		if(!isValidReleaseDate(movie.getReleaseDate()))
			errMsgs.add("Release Date should not be a future date");
		
		if(errMsgs.size()==0)
			isValid=true;
		else
			throw new MovieException(errMsgs.toString());
		
		return isValid;
	}


	@Override
	public String add(Movie movie) throws MovieException {
		String mvId=null;
		if(movie!=null && isValidMovie(movie)){
			mvId=movieDao.add(movie);
		}
		return mvId;
	}

	@Override
	public boolean delete(String mvId) throws MovieException {
		boolean isDone=false;
		if(mvId!=null && isValidMvId(mvId)){
			movieDao.delete(mvId);
			isDone = true;
		}else{
			throw new MovieException("mvId should be a capital letter followed by 3 digits");
		}
		return isDone;
	}

	@Override
	public Movie searchMvId(String mvId) throws MovieException {
		return movieDao.searchId(mvId);
	}
	
	@Override
	public Movie searchMvName(String mvName) throws MovieException {
		return movieDao.searchName(mvName);
	}
	
	@Override
	public Movie searchMvHero(String mvHero) throws MovieException {
		return movieDao.searchHero(mvHero);
	}
	
	@Override
	public Movie searchRDate(LocalDate releaseDate) throws MovieException {
		return movieDao.searchDate(releaseDate);
	}
	
	@Override
	public Movie searchCollection(Double collection) throws MovieException {
		return movieDao.searchCollection(collection);
	}

	@Override
	public List<Movie> getAll() throws MovieException {
		return movieDao.getAll();
	}

	@Override
	public boolean update(Movie movie) throws MovieException {
		boolean isDone = false;
		
		if(movie!=null && isValidMovie(movie)){
			isDone = movieDao.update(movie);
		}
		
		return isDone;
	}


	@Override
	public void persist() throws MovieException {
		movieDao.persist();
	}
}

