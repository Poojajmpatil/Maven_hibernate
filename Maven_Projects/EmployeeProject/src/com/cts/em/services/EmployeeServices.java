package com.cts.em.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cts.em.dao.EmployeeIODAO;
import com.cts.em.dao.IEmployeeDAO;
import com.cts.em.exception.EmployeeException;
import com.cts.em.model.Employee;



public class EmployeeServices implements IEmployeeServices {
	
private IEmployeeDAO employeeDao;
	
	public IEmployeeDAO getDao() {
		return employeeDao;
	}
	
	public EmployeeServices()throws EmployeeException{
		employeeDao= new EmployeeIODAO();
	}

	public boolean isValidEmployeeId(String eId) {
		Pattern employeeIdPattern = Pattern.compile("[A-Z]\\d{4}");
		Matcher employeeIdMatcher = employeeIdPattern.matcher(eId);
		return employeeIdMatcher.matches();
	}
	
	public boolean isValidEmployeeName(String name) {
		Pattern employeeNamePattern = Pattern.compile("[A-Za-z]\\w{2,30}");
		Matcher employeeNameMatcher = employeeNamePattern.matcher(name);
		return employeeNameMatcher.matches();
	}
	
	public boolean isValidDateOfBirth(LocalDate date) {
		LocalDate today = LocalDate.now();
		return today.isAfter(date)||date.equals(today);
	}
	
	public boolean isValidSalary(double earning) {
		return earning>=1000;
	}
	public boolean isValidEmployee(Employee emp) throws EmployeeException {
		boolean isValid= false;
		
		List<String> errMsgs = new ArrayList<>();
		if(!isValidEmployeeId(emp.getEmployeeId()))
			errMsgs.add("Employee id should start with a Capital Letter followed by 4 digits");
		if(!isValidEmployeeName(emp.getName()))
			errMsgs.add("Employee name should not contain any special characters or spaces");
		if(!isValidDateOfBirth(emp.getDateOfBirth()))
			errMsgs.add("Employee's date of birth cannot be today");
		
		if(errMsgs.size()==0)
			isValid= true;
		else
			throw new EmployeeException(errMsgs.toString());
		
		return isValid;
	}

	@Override
	public String add(Employee employee) throws EmployeeException {
		String employeeId= null;
		if(employee != null && isValidEmployee(employee))
			employeeId = employeeDao.add(employee);
		
		return employeeId;
	}

	@Override
	public boolean remove(String employeeId) throws EmployeeException {
		boolean isDone= false;
		if(employeeId != null && isValidEmployeeId(employeeId)) {
			employeeDao.remove(employeeId);
			isDone = true;
		}else {
			throw new EmployeeException("Employee with id "+employeeId+" not found");
		}
		return isDone;
	}

	@Override
	public Employee get(String employee) throws EmployeeException {
		// TODO Auto-generated method stub
		return employeeDao.get(employee);
	}

	@Override
	public List<Employee> getAll() throws EmployeeException {
		// TODO Auto-generated method stub
		return employeeDao.getAll();
	}

	@Override
	public boolean update(Employee employeeId) throws EmployeeException {
		boolean isDone = false;
		if(employeeId!=null && isValidEmployee(employeeId))
			isDone= employeeDao.update(employeeId);
		return isDone;
	}

	@Override
	public void persist() throws EmployeeException {
		// TODO Auto-generated method stub
		employeeDao.persist();
	}

	

}
