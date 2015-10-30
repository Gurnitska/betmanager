package com.gurnitskaya.bmanager.tests;

import org.junit.Test;

import com.gurnitskaya.bmanager.beans.Type;
import com.gurnitskaya.bmanager.dao.TypeDAO;
import com.gurnitskaya.bmanager.impl.TypeImplDAO;

public class TypeTest {
	@Test
	public void testGetAllLeagues() {
//		Type type = new Type();
//		type.setName("f(-1)");
		
		TypeDAO typeImpl = new TypeImplDAO();
//		typeImpl.addType(type);
		System.out.println(typeImpl.getAllTypes());
		
	}
}
