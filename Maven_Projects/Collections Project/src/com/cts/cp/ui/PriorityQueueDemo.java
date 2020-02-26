package com.cts.cp.ui;

import java.util.PriorityQueue;
import java.util.Scanner;

import com.cts.cp.model.StudentComparator;
import com.cts.cp.model.StudentQueue;

public class PriorityQueueDemo {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		PriorityQueue<StudentQueue> pq = new PriorityQueue<StudentQueue>(5, new StudentComparator());
		StudentQueue student1 = new StudentQueue("Nandini", 3.2);
		pq.add(student1); 
        StudentQueue student2 = new StudentQueue("Anmol", 3.6); 
        pq.add(student2);          
        StudentQueue student3 = new StudentQueue("Palak", 4.0); 
        pq.add(student3); 
        System.out.println("Students served in their priority order");
        while (!pq.isEmpty()) { 
            System.out.println(pq.poll().getName()); 
	}

}
}


