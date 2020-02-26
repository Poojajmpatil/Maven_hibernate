package com.cts.mv.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cts.mv.exception.MovieException;
import com.cts.mv.model.Movie;
import com.cts.mv.util.ConnectionProvider;

public class MovieDAOJDBCImpl implements IMovieDAO {
		ConnectionProvider conProvider;
		private Map<String, Movie> movies;

		public MovieDAOJDBCImpl() throws MovieException {
			
			try {
				conProvider = ConnectionProvider.getInstance();
			} catch (IOException | ClassNotFoundException exp) {
				
				throw new MovieException("Database is not reachable");
			}
		}
		public String add(Movie movie) throws MovieException {
			String mvId = null;
			if (movie != null) {
				try (Connection con = conProvider.getConnection();
						PreparedStatement pInsert = con
								.prepareStatement(IQueryMapper.ADD_MOVIE_QRY)) {

					pInsert.setString(1, movie.getMvId());
					pInsert.setString(2, movie.getMvName());
					pInsert.setString(3,movie.getMvHero());
					pInsert.setDate(4, Date.valueOf(movie.getReleaseDate()));
					pInsert.setInt(5,movie.getCollection());

					int rowCount = pInsert.executeUpdate();

					if (rowCount == 1) {
						mvId = movie.getMvId();
					}
				} catch (SQLException exp) {
					throw new MovieException("Movie is not inserted");
				}
			}
			return mvId;
		}
		
		@Override
		public boolean delete(String mvId) throws MovieException {
			boolean isDone = false;

			try (Connection con = conProvider.getConnection();
					PreparedStatement pDelete = con
							.prepareStatement(IQueryMapper.DEL_MOVIE_QRY)) {

				pDelete.setString(1, mvId);

				int rowCount = pDelete.executeUpdate();

				if (rowCount == 1) {
					isDone = true;
				}
			} catch (SQLException exp) {
				throw new MovieException("Movie is not deleted");
			}

			return isDone;
		}
		
		
		
		@Override
		public List<Movie> getAll() throws MovieException {
			List<Movie> movies=null;
			try (Connection con = conProvider.getConnection();
					PreparedStatement pSelect = con
							.prepareStatement(IQueryMapper.GET_ALL_MOVIES_QRY)) {

				ResultSet rs = pSelect.executeQuery();
				
				movies = new ArrayList<Movie>();
				
				while(rs.next()){
					Movie movie = new Movie();
					movie.setMvId(rs.getString("mvId"));
					movie.setMvName(rs.getString("mvName"));
					movie.setMvHero(rs.getString("mvHero"));
					movie.setReleaseDate(rs.getDate("releaseDate").toLocalDate());
					movie.setCollection(rs.getInt("collection"));
					movies.add(movie);
				}
				
			} catch (SQLException exp) {
				throw new MovieException("No Movies are available.");
			}		
			return movies;	
		}
		
		@Override
		public boolean update(Movie movie) throws MovieException {
			boolean isDone = false;
			if (movie != null) {
				try (Connection con = conProvider.getConnection();
						PreparedStatement pUpdate = con
								.prepareStatement(IQueryMapper.MODIFY_MOVIE_QRY)) {

					
					pUpdate.setString(1, movie.getMvName());
					pUpdate.setString(2, movie.getMvHero());
					pUpdate.setDate(3, Date.valueOf(movie.getReleaseDate()));
					pUpdate.setInt(4, movie.getCollection());
					pUpdate.setString(5, movie.getMvId());
					

					int rowCount = pUpdate.executeUpdate();

					if (rowCount == 1) {
						isDone = true;
					}
				} catch (SQLException exp) {
					throw new MovieException("Movie is not updated.");
				}
			}
			return isDone;
		}
		
		@Override
		public void persist() throws MovieException {
			/* no implementation required */
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
	}


