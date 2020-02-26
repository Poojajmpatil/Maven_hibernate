package com.cts.cp.ui;


import java.util.Map;
import java.util.TreeMap;

public class TreeMapClient {

	public static void main(String[] args) {
				Map<String,String> hm=new TreeMap<>();
				hm.put("java","Language");
				hm.put("dotnet","Framework");
				hm.put("c#","Language");
				for(String data : hm.keySet())
					System.out.printf("%s : %s\n",data,hm.get(data));
			}

	}
