package com.cts.cp.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListClient {

	public static void main(String[] args)throws IOException {
		Scanner scan=new Scanner(System.in);
		List<String> names=new ArrayList<>();
		String name=null;
		while(true) {
			System.out.println("Enter name :");
			name=scan.nextLine();
			if(name.equalsIgnoreCase("end"))
				break;
		names.add(name);
		}
		for(String data : names)
			System.out.println(data);
	}

}
