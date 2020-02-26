package com.cts.mhc.ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.cts.mhc.exception.MovieHallException;
import com.cts.mhc.model.Movie;
import com.cts.mhc.model.MovieHallAppMenu;
import com.cts.mhc.service.MovieServiceImpl;
import com.cts.mhc.service.IMovieService;

public class MovieHallManagementUI {

	private static IMovieService movieService;
	private static Scanner scan;
	private static DateTimeFormatter dtFormater;

	public static void main(String[] args) {

		try {
			movieService = new MovieServiceImpl();
		} catch (MovieHallException e) {
			e.printStackTrace();
		}


		scan = new Scanner(System.in);
		dtFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		MovieHallAppMenu menu = null;

		while (menu != MovieHallAppMenu.QUIT) {

			System.out.println("Choice\tMenu Item");
			System.out.println("===========================");
			for (MovieHallAppMenu m : MovieHallAppMenu.values()) {
				System.out.println(m.ordinal() + "\t" + m.name());
			}
			System.out.print("Choice: ");
			int choice = -1;
			if (scan.hasNextInt())
				choice = scan.nextInt();
			else {
				scan.next();
				System.out.println("Pleae choose a choice displayed");
				continue;
			}

			if (choice < 0 || choice >= MovieHallAppMenu.values().length) {
				System.out.println("Invalid Choice");
			} else {
				menu = MovieHallAppMenu.values()[choice];
				switch (menu) {
				case ADD:
					doAdd();
					break;
				case LIST:
					doList();
					break;
				case SEARCH:
					doSearch();
					break;
				case REMOVE:
					doRemove();
					break;
				case QUIT:
					System.out.println("ThanQ Come Again!");
					break;
				}
			}

		}

		scan.close();
		try {
			movieService.persist();
		} catch (MovieHallException e) {
			e.printStackTrace();
		}

	}

	private static void doAdd() {
		try {
			Movie Movie = new Movie();
			System.out.print("Movie Hall: ");
			Movie.setName(scan.next());
			System.out.print("Movie Name: ");
			Movie.setMovie(scan.next());
			System.out.print("PublishDate(dd-MM-yyyy): ");
			String pubDtStr = scan.next();

			try {
				Movie.setDate(LocalDate.parse(pubDtStr, dtFormater));
			} catch (DateTimeException exp) {
				throw new MovieHallException(
						"Date must be in the format day(dd)-month(MM)-year(yyyy)");
			}
			System.out.print("Ticket Price: ");
			if (scan.hasNextDouble())
				Movie.setTicket(scan.nextDouble());
			else {
				scan.next();
				throw new MovieHallException("Price is a number");
			}

			String movie_Name= movieService.add(Movie);
			System.out.println("Movie is Added with code: " + movie_Name);
		} catch (MovieHallException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doList() {
		List<Movie> Movies;
		try {
			Movies = movieService.getAll();
			if (Movies.size() == 0) {
				System.out.println("No Movies To display");
			} else {
				for (Movie b : Movies)
					System.out.println(b);
			}
		} catch (MovieHallException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doSearch() {
		System.out.print("Movie Name: ");
		String movie_name = scan.next();

		try {
			Movie Movie = movieService.get(movie_name);
			if (Movie != null) {
				System.out.println(Movie);
			} else {
				System.out.println("No Such Movie");
			}
		} catch (MovieHallException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doRemove() {
		System.out.print("movie_Hall: ");
		String movie_name = scan.next();
		try {
			boolean isDone = movieService.delete(movie_name);
			if (isDone) {
				System.out.println("Movie is Deleted");
			} else {
				System.out.println("No Such Movie");
			}
		} catch (MovieHallException exp) {
			System.out.println(exp.getMessage());
		}
	}
}
