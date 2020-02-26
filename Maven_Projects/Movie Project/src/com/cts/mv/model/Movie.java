package com.cts.mv.model;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Movie implements Serializable /* implements Comparable<Book> */ {

	private String mvId;
	private String mvName;
	private String mvHero;
	private LocalDate releaseDate;
	private int collection;
	
	public Movie() {
		/* default constructor */
	}
	public Movie(String mvId, String mvName, String mvHero,LocalDate releaseDate, int collection) {
		super();
		this.mvId = mvId;
		this.mvName = mvName;
		this.mvHero=mvHero;
		this.releaseDate = releaseDate;
		this.collection = collection;
	}

	public String getMvId() {
		return mvId;
	}

	public void setMvId(String mvId) {
		this.mvId = mvId;
	}

	public String getMvName() {
		return mvName;
	}

	public void setMvName(String mvName) {
		this.mvName = mvName;
	}
	
	public String getMvHero() {
		return mvHero;
	}

	public void setMvHero(String mvHero) {
		this.mvHero = mvHero;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getCollection() {
		return collection;
	}

	public void setCollection(int collection) {
		this.collection = collection;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("Movie Id : ");
		output.append(mvId);
		output.append("\tMovie Name : ");
		output.append(mvName);
		output.append("Hero of the Movie :");
		output.append(mvHero);
		output.append("\tRelease Date :");
		output.append(releaseDate);
		output.append("\tMovie Collection : ");
		output.append(collection);
		return output.toString();
		
	}

	
	/*
	@Override
	public int compareTo(Book book) {
		String firstmvId = this.mvId;
		String secondmvId = book.mvId;
		return firstmvId.compareTo(secondmvId);
	}
		
	@Override
	public int hashCode() {
		int hashCode =0;
		char[] chars = mvId.toCharArray();
		for(int i=1;i<=chars.length;i++){
			hashCode += ((int)chars[i-1])*i;
		}
		
		return hashCode;
	}
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if (obj instanceof Book) {
			Book book = (Book)obj;
			String firstmvId = this.mvId;
			String secondmvId = book.mvId;
			flag= firstmvId.equals(secondmvId);
		}
		
		return flag;		
	}
*/
}
