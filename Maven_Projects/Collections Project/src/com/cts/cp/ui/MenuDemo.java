package com.cts.cp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.cts.cp.model.Menu;

public class MenuDemo {
	public static void main(String[] args) {
		ArrayList<String> ShopList=new ArrayList<>();
		Menu menu = null;
		String name;
		BufferedReader br=new  BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(System.in);
		while (menu != Menu.QUIT) {
			System.out.println("Choice\tMenu Item");
			System.out.println("===========================");
			for (Menu m : Menu.values()) {
				System.out.println(m.ordinal() + "\t" + m.name());
			}
			System.out.print("Choice: ");
			int choice = -1;
			if (scan.hasNextInt())
				choice = scan.nextInt();
			else {
				scan.next();
				System.out.println("Pleae choose a choice displayed");
				continue;
			}

			if (choice < 0 || choice >= Menu.values().length) {
				System.out.println("Invalid Choice");
			} else {
					menu = Menu.values()[choice];
					try {
					switch (menu) {
						case ADD:
							System.out.println("Enter list of items u want to shop");
							name=br.readLine();
							ShopList.add(name);
							System.out.println(name+" has been added into the bucket");
							break;
					case LIST:
							System.out.println("List of items in the bucket");
							Iterator<String> iterator=ShopList.iterator();
							while(iterator.hasNext()) {
								System.out.println(iterator.next()+" ");
							}
							break;
					case REMOVE:
							System.out.println("Enter item to remove from the bucket");
							name=br.readLine();
							ShopList.remove(name);			
							System.out.println(name+" has been removed from the list");
							break;
					case QUIT:
						System.out.println("ThanQ For Shopping.. Come Again!");
						break;
					}
				}catch(IOException exep){
					exep.printStackTrace();	
				}

			}
		}
	}
	}


