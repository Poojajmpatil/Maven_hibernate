package com.cts.cp.ui;

import java.util.HashMap;
import java.util.Map;

public class HashMapClient {

	public static void main(String[] args) {
		Map<String,String> hm=new HashMap<>();
		hm.put("java","Language");
		hm.put("dotnet","Framework");
		hm.put("c#","Language");
		for(String data : hm.keySet())
			System.out.printf("%s : %s\n",data,hm.get(data));
	}

}
