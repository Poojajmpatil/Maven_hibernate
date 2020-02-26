package com.cts.cp.ui;

import java.util.Set;
import java.util.TreeSet;

import com.cts.cp.model.Person;

public class PersonCollectionsClient {

	public static void main(String[] args) {
		Set<Person> persons=new TreeSet<>();
		persons.add(new Person("Meena",21));
		persons.add(new Person("Soumya",93));
		persons.add(new Person("Vani",21));
		for(Person data : persons)
			System.out.println(data);
	}

}
