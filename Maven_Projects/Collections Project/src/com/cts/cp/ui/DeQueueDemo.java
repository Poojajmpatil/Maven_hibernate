package com.cts.cp.ui;

import java.util.Deque;
import java.util.LinkedList;

public class DeQueueDemo {

	public static void main(String[] args) {
		
		
		Deque<String> dq = new LinkedList<String>();
		    
	      dq.add("Shikhar");
	      dq.add("Dhoni");
	      dq.add("Rohit");
	      dq.add("Bhuvi"); 
	      dq.add("Bumrah");
	      dq.add("Kohli");
	      
	      System.out.println("Elements in Queue:"+dq);
	      System.out.println("Removed element: "+dq.remove());
	      System.out.println("Head: "+dq.element());
	      System.out.println("peek(): "+dq.peek());
	      System.out.println("poll(): "+dq.poll());
	      System.out.println("Elements in Queue:"+dq);

	}

}
