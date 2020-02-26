package com.cts.em.model;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Employee implements Serializable,Comparable<Employee> {
	private String employeeId;
	private String name;
	private LocalDate dateOfBirth;
	private double salary;
	
	public Employee() {
		super();
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Employee(String employeeId, String name, LocalDate dateOfBirth, double salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", salary="
				+ salary + "]";
	}

	@Override
	public int compareTo(Employee e) {
		return getDateOfBirth().compareTo(e.getDateOfBirth());
	}
	
	
}
