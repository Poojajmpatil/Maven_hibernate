package com.cts.mhc.model;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Represents a book in a library or book store.
 */
@SuppressWarnings("serial")
public class Movie implements Serializable /* implements Comparable<Book> */ {

	private String hall_Name;
	private String movie_Name;
	private LocalDate release_Date;
	private double ticket_price;

	public Movie() {
		/* default constructor */
	}

	public Movie(String hall_Name, String movie_Name, LocalDate release_Date, double ticket_price) {
		super();
		this.hall_Name = hall_Name;
		this.movie_Name = movie_Name;
		this.release_Date = release_Date;
		this.ticket_price = ticket_price;
	}

	public String getName() {
		return hall_Name;
	}

	public void setName(String hall_Name) {
		this.hall_Name = hall_Name;
	}

	public String getMovie() {
		return movie_Name;
	}

	public void setMovie(String movie_Name) {
		this.movie_Name = movie_Name;
	}
	public LocalDate getDate() {
		return release_Date;
	}

	public void setDate(LocalDate release_Date) {
		this.release_Date = release_Date;
	}

	public double getTicket() {
		return ticket_price;
	}

	public void setTicket(double ticket_price) {
		this.ticket_price = ticket_price;
	}


	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("Hall Name : ");
		output.append(hall_Name);
		output.append("\tMovie Name : ");
		output.append(movie_Name);
		output.append("\t\t Release Date :");
		output.append(release_Date);
		output.append("\tticket_price price : ");
		output.append(ticket_price);
		return output.toString();
	}
	
	/*
	@Override
	public int compareTo(Book book) {
		String firstBCode = this.bcode;
		String secondBCode = book.bcode;
		return firstBCode.compareTo(secondBCode);

		
	@Override
	public int hashCode() {
		int hashCode =0;

		char[] chars = bcode.toCharArray();
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
			String firstBCode = this.bcode;
			String secondBCode = book.bcode;
			flag= firstBCode.equals(secondBCode);
		}
		
		return flag;		
	}
*/
}
