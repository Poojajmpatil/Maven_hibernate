package com.cts.em.services;

import java.util.Comparator;

import com.cts.em.model.Employee;


public class EmployeeAgeComparator implements Comparator<Employee>{
	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
	}
}
