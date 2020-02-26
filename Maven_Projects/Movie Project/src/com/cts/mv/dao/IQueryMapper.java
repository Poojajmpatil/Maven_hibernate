package com.cts.mv.dao;

public interface IQueryMapper {

		public static final String ADD_MOVIE_QRY = 
				"INSERT INTO movies(mvId,  mvName, mvHero, releaseDate,collection) VALUES(?,?,?,?,?)";
		public static final String MODIFY_MOVIE_QRY = 
				"UPDATE movies SET mvName=?,mvHero=?,releaseDate=?, collection=? WHERE mvId=?";
		public static final String DEL_MOVIE_QRY = 
				"DELETE FROM movies WHERE mvId=?";
		public static final String GET_ALL_MOVIES_QRY = 
				"SELECT * FROM movies";
		public static final String GET_MOVIES_QRY = 
				"SELECT * FROM movies WHERE mvId=?";
	}


