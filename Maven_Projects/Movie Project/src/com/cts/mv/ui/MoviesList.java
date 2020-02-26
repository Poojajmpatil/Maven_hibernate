package com.cts.mv.ui;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.cts.mv.exception.MovieException;
import com.cts.mv.model.Movie;
import com.cts.mv.model.MovieMenu;
import com.cts.mv.service.IMovieService;
import com.cts.mv.service.MovieComparatorCollection;
import com.cts.mv.service.MovieComparatorDate;
import com.cts.mv.service.MovieComparatorHero;
import com.cts.mv.service.MovieComparatorName;
import com.cts.mv.service.MovieServiceImpl;

public class MoviesList {
	private static IMovieService movieService;
	private static Scanner scan;
	private static DateTimeFormatter dtFormater;
	public static void main(String[] args) {

		try {
			movieService = new MovieServiceImpl();
		} catch (MovieException e) {
			e.printStackTrace();
		}


		scan = new Scanner(System.in);
		dtFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		MovieMenu menu = null;

		while (menu != MovieMenu.QUIT) {

			System.out.println("Choice\tMenu Item");
			System.out.println("===========================");
			for (MovieMenu m : MovieMenu.values()) {
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

			if (choice < 0 || choice >= MovieMenu.values().length) {
				System.out.println("Invalid Choice");
			} else {
				menu = MovieMenu.values()[choice];
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
				case HERO_WISE:
					doHero();
					break;
				case DATE_WISE:
					doDate();
					break;
				case COLLECTION_WISE:
					doCollection();
					break;
				case TITLE_WISE:
					doTitle();
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
		} catch (MovieException e) {
			e.printStackTrace();
		}

	}

	private static void doAdd() {
		try {
			Movie movie = new Movie();
			System.out.print("Movie Id: ");
			movie.setMvId(scan.next());
			System.out.print("Movie Name: ");
			movie.setMvName(scan.next());
			System.out.print("Movie Hero: ");
			movie.setMvHero(scan.next());
			System.out.print("Release Date(dd-MM-yyyy): ");
			String pubDtStr = scan.next();

			try {
				movie.setReleaseDate(LocalDate.parse(pubDtStr, dtFormater));
			} catch (DateTimeException exp) {
				throw new MovieException(
						"Date must be in the format day(dd)-month(MM)-year(yyyy)");
			}
			System.out.print("Collection of Movie: ");
			if (scan.hasNextInt())
				movie.setCollection(scan.nextInt());
			else {
				scan.next();
				throw new MovieException("Collection is a number");
			}

			String mvId =movieService.add(movie);
			System.out.println("Movie is Added with Id: " + mvId);
		} catch (MovieException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doList() {
		List<Movie> movies;
		try {
			movies = movieService.getAll();
			if (movies.size() == 0) {
				System.out.println("No Moviess To display");
			} else {
				for (Movie m : movies)
					System.out.println(m);
			}
		} catch (MovieException exp) {
			System.out.println(exp.getMessage());
		}
	}

	private static void doSearch() {
		System.out.print("Movie Id: ");
		String mvId = scan.next();

		try {
			Movie movie = movieService.searchMvId(mvId);
			if (movie != null) {
				System.out.println(movie);
			} else {
				System.out.println("No Such Movie");
			}
		} catch (MovieException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doHero() {
		List<Movie> movies;
		try {
			movies = movieService.getAll();
			if(movies.size()==0) {
				System.out.println("No movies to display");
			}else {
					Collections.sort(movies, new MovieComparatorHero());
					for(Movie m : movies) {
						System.out.println(m);
					}
					System.out.println("------------------------------------------------------------------------------------------------------------");
			}
		}catch (MovieException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doDate() {
		List<Movie> movies;
		try {
			movies = movieService.getAll();
			if(movies.size()==0) {
				System.out.println("No movies to display");
			}else {
				Collections.sort(movies, new MovieComparatorDate());
				for(Movie movie : movies) {
					System.out.println(movie);
				}
				System.out.println("------------------------------------------------------------------------------------------------------------");
			}
		}catch (MovieException exp) {
			System.out.println(exp.getMessage());
		}
	}
	private static void doTitle() {
		List<Movie> movies;
		try {
			movies = movieService.getAll();
			if(movies.size()==0) {
				System.out.println("No movies to display");
			}else {
					Collections.sort( movies,new MovieComparatorName());
					System.out.println("Movie list sorted according to their Titles:");
					for(Movie m: movies)
						System.out.println(m);
					System.out.println("------------------------------------------------------------------------------------------------------------");
			}
		}catch (MovieException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doCollection() {
		List<Movie> movies;
		try {
			movies = movieService.getAll();
			if(movies.size()==0) {
				System.out.println("No movies to display");
			}else {
		Collections.sort(movies, new MovieComparatorCollection());
		for(Movie movie : movies) {
			System.out.println(movie);
			}
		System.out.println("------------------------------------------------------------------------------------------------------------");
	}
		}catch (MovieException exp) {
			System.out.println(exp.getMessage());
	}
	}
	private static void doRemove() {
		System.out.print("Movie Id: ");
		String mvId = scan.next();
		try {
			boolean isDone = movieService.delete(mvId);
			if (isDone) {
				System.out.println("Movie is Deleted");
			} else {
				System.out.println("No Such Movie");
			}
		} catch (MovieException exp) {
			System.out.println(exp.getMessage());
		}
	}
}
