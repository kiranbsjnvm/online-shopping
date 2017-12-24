package com.myweb.onlineshoppingBackend.dao;

import java.util.List;

import com.myweb.onlineshoppingBackend.dto.Category;

public interface CategoryDAO {
	
	Category get(int id);
	
	List<Category> list();
	
	boolean add(Category category);
	
	boolean delete(Category category);
	
	boolean update(Category category);
}
