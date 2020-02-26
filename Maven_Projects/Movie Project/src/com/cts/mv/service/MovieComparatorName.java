package com.cts.mv.service;

import java.util.Comparator;

import com.cts.mv.model.Movie;

public class MovieComparatorName implements Comparator<Movie> 
	{ 	
		@Override
	    public int compare(Movie a, Movie b) 
	    { 
	        return a.getMvName().compareTo(b.getMvName());
	    } 
	} 
	  
