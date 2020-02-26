package com.cts.cp.ui;

import java.util.Iterator;
import java.util.Vector;

public class VectorClient {

	public static void main(String[] args) {
		Vector<String> v=new Vector<>();
		v.add("Meena");
		v.add("Soumya");
		Iterator<String> it=v.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
//		for(String data : v)
//			System.out.println(data);
	}

}
