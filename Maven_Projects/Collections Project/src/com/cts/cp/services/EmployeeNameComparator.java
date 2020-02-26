package com.cts.cp.services;

import java.util.Comparator;

import com.cts.cp.model.Employee;

public class EmployeeNameComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee first, Employee other) {
		return first.getName().compareTo(other.getName());
	}

}
