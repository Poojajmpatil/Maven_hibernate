package com.cts.cp.ui;

import java.util.Stack;

public class StackDemo {

	public static void main(String[] args) {
		 Stack<String> typeOfGames = new Stack<>();

	       
		 typeOfGames.push("Cricket");
		 typeOfGames.push("Batminton");
		 typeOfGames.push("Hockey");
		 typeOfGames.push("Chess");
		 typeOfGames.push("Tennis");

	        System.out.println("Stack => " + typeOfGames);
	        System.out.println();

	       
	        String gameAtTop = typeOfGames.pop();  
	        System.out.println("Stack.pop() => " + gameAtTop);
	        System.out.println("Current Stack => " + typeOfGames);
	        System.out.println();

	       
	        gameAtTop = typeOfGames.peek();
	        System.out.println("Stack.peek() => " + gameAtTop);
	        System.out.println("Current Stack => " + typeOfGames);

	}



}
