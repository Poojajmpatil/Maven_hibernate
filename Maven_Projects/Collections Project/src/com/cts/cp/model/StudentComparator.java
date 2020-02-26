package com.cts.cp.model;

import java.util.Comparator;

public class StudentComparator implements Comparator<StudentQueue>{
	public int compare(StudentQueue s1, StudentQueue s2) { 
	      if (s1.cgpa < s2.cgpa) 
	               return 1; 
	       else if (s1.cgpa > s2.cgpa) 
	                return -1; 
	       return 0; 
	 }
} 



