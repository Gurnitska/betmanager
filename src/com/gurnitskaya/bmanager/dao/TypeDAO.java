package com.gurnitskaya.bmanager.dao;

import java.util.List;

import com.gurnitskaya.bmanager.beans.Type;

public interface TypeDAO {

	public void addType(Type type); 

	public void updateType(Type type);

	public Type getTypeById(Long id); 

	public List<Type> getAllTypes(); 

	public void deleteType(Type type);
}
