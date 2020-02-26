package com.cts.em.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.cts.em.exception.EmployeeException;
import com.cts.em.model.Employee;


public class EmployeeIODAO implements IEmployeeDAO {
	
	public static final String DATA_STORE_FILE_NAME = "employee.txt";
	private Map<String, Employee> employee;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EmployeeIODAO() throws EmployeeException{
		File file= new File(DATA_STORE_FILE_NAME);
		
		if(!file.exists()) {
			employee = new TreeMap();
		} else {
			try(ObjectInputStream fin = new ObjectInputStream(
					new FileInputStream(DATA_STORE_FILE_NAME))) {
				Object obj = fin.readObject();
				
				if(obj instanceof Map) {
					employee = (Map<String, Employee>) obj;
				} else {
					throw new EmployeeException("Not a valid DataStore");
				}
			} catch (IOException | ClassNotFoundException exp ) {
				throw new EmployeeException("Loading data failed");
			}
		}
	}
	@Override
	public String add(Employee employee) throws EmployeeException {
		// TODO Auto-generated method stub
		String emp= null;
		if(employee!=null) {
			emp= employee.getEmployeeId();
			this.employee.put(emp, employee);
		}
		return emp;
	}

	@Override
	public boolean remove(String employeeId) throws EmployeeException {
		boolean isDone= false;
		
		if(employee!=null) {
			employee.remove(employeeId);
			isDone= true;
		}
		return isDone;
	}

	@Override
	public Employee get(String employeeId) throws EmployeeException {
		// TODO Auto-generated method stub
		return employee.get(employeeId);
	}

	@Override
	public List<Employee> getAll() throws EmployeeException {
		return new ArrayList<>(employee.values());
	}

	@Override
	public boolean update(Employee employee) throws EmployeeException {
		boolean isDone= false;
		if(employee!=null) {
			String empId= employee.getEmployeeId();
			this.employee.replace(empId, employee);
		}
		return isDone;
	}

	@Override
	public void persist() throws EmployeeException {
		try (ObjectOutputStream fout = new ObjectOutputStream(
				new FileOutputStream(DATA_STORE_FILE_NAME))) {
			fout.writeObject(employee);
		} catch (IOException exp) {
			throw new EmployeeException("Saving Data Failed");
		}

	}

}
