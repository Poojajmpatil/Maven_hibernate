package com.cts.cp.model;

public class StudentQueue {
	public String name;
	public double cgpa;
	public StudentQueue(String name,double cgpa) {
		this.name = name;
		this.cgpa=cgpa;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	
	
}
