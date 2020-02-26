package com.cts.em.dao;

import java.util.List;

import com.cts.em.exception.EmployeeException;
import com.cts.em.model.Employee;

public interface IEmployeeDAO {
		String add(Employee employee) throws EmployeeException;
		boolean remove(String employeeId) throws EmployeeException;
		Employee get(String employee) throws EmployeeException;
		List<Employee> getAll() throws EmployeeException;
		boolean update(Employee employeeId) throws EmployeeException;
		public void persist() throws EmployeeException;

}
