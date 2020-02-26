package com.cts.cp.ui;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListCollectionClass {

	public static void main(String[] args) {
	ArrayList<String> names=new ArrayList<>();
	names.add("James Gosling");
	names.add("Meena");
	names.add("Zebra");
	names.add("Alan Cooper");
	Collections.sort(names);
	printList(names);
	System.out.println("-----------------------");
	int pos=Collections.binarySearch(names,"Alan Cooper");
	System.out.printf("Alan Cooper is found at : %d\n",pos);
	System.out.printf("Max is %s \n",Collections.max(names));
	System.out.println("-------------");
	Collections.reverse(names);
	printList(names);
	}
	private static void printList(ArrayList<String>names) {
		for(String name : names)
			System.out.println(name);
	}

}
