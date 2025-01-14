package com.mavin.SapConnectService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestGeneric {

	@Test
	void test() {
		List<String> list = new ArrayList<String>();
		list.add("sdfsdf");
		func(list);
	}
	
	public void func(List<?> list) {
		
		for(Object o : list.getClass().getTypeParameters()) {
			System.out.println(o.getClass());
		}
		
		list.forEach(o -> {
			System.out.println(o.getClass());
		});
		
	}

}
