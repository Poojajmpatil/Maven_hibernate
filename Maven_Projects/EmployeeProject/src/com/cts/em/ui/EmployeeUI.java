package com.cts.em.ui;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
//import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.cts.em.exception.EmployeeException;
import com.cts.em.model.Employee;
import com.cts.em.model.EmployeeMenu;
import com.cts.em.services.EmployeeAgeComparator;
import com.cts.em.services.EmployeeServices;
import com.cts.em.services.IEmployeeServices;

public class EmployeeUI {
	private static IEmployeeServices employeeService;
	private static Scanner sc;
	private static DateTimeFormatter dtFormater;	
	public static void main(String[] args) {
		try {
			employeeService = new EmployeeServices();
		}catch (EmployeeException e) {
			e.printStackTrace();
		}
		
		sc = new Scanner(System.in);
		dtFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		EmployeeMenu menu = null;
		
		while(menu != EmployeeMenu.QUIT) {
			System.out.println("Choice \tMenu Item");
			System.out.println("----------------------------");
			for(EmployeeMenu m : EmployeeMenu.values()) {
				System.out.println(m.ordinal()+"\t"+m.name());
			}
			System.out.println("Choice: ");
			int choice = -1;
			if(sc.hasNext())
				choice = sc.nextInt();
			else {
				sc.next();
				System.out.println("Please choose a choice displayed");
				continue;
			}
			
			if(choice > 0 || choice <= EmployeeMenu.values().length ) {
				
				menu = EmployeeMenu.values()[choice];
				switch (menu) {
				case ADD:
					doAdd();
					break;
				case REMOVE:
					doRemove();
					break;
				case SEARCH:
					doSearch();
					break;
				case LIST:
					doList();
					break;
				case QUIT:
					System.out.println("The program has ended");
					break;
				
				}
			}else
				System.out.println("Wrong choice");
		}
		sc.close();
		try {
			employeeService.persist();
		} catch (EmployeeException e) {
			e.printStackTrace();
		}
	}
	
	private static void doAdd() {
		try {
			Employee emp = new Employee();
			System.out.print("Employee Id: ");
			emp.setEmployeeId(sc.next());
			System.out.print("Employee Title: ");
			emp.setName(sc.next());
			System.out.print("Date of Birth: ");
			String bDate = sc.next();
			System.out.print("Salary: ");
			emp.setSalary(sc.nextDouble());
			try {
				emp.setDateOfBirth(LocalDate.parse(bDate, dtFormater));
			}catch (DateTimeException exp) {
				throw new EmployeeException("Date must be in the format dd-MM-yyyy");
			}
			
			String empId = employeeService.add(emp);
			System.out.println("Employee is added with ID: "+empId);
		}catch(EmployeeException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doList() {
		List<Employee> emp;
		try {
			emp = employeeService.getAll();
			if(emp.size()==0) {
				System.out.println("No employees to display");
			}else {
				Collections.sort(emp, new EmployeeAgeComparator());
				System.out.println("Employee list sorted according to their age:");
				for(Employee m: emp)
					System.out.println(m);
				/*System.out.println("------------------------------------------------------------------------------------------------------------");
				Collections.sort(movie, new MovieDateComparator());
				System.out.println("Movie list sorted according to the release dates");
				for(Employee m: movie)
					System.out.println(m);
				System.out.println("------------------------------------------------------------------------------------------------------------");
				Collections.sort(movie, new MovieIdComparator());
				System.out.println("Movie list sorted according to the IDs");
				for(Employee m: movie)
					System.out.println(m);
				System.out.println("------------------------------------------------------------------------------------------------------------");*/
			}
		}catch (EmployeeException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doSearch() {
		System.out.print("Employee Id: ");
		String empId = sc.next();
		try {
			Employee emp = employeeService.get(empId);
			if(emp != null) {
				System.out.println(emp);
			} else {
				System.out.println("No such employees");
			}
		}catch (EmployeeException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	private static void doRemove() {
		System.out.println("Employee Id: ");
		String mId = sc.next();
		try {
			Employee emp = employeeService.get(mId);
			if(emp!=null) {
				employeeService.remove(mId);
				System.out.println("Employee is deleted");
			} else {
				System.out.println("No such movie");
			}
		}catch (EmployeeException exp) {
			System.out.println(exp.getMessage());
		}
	}
}
