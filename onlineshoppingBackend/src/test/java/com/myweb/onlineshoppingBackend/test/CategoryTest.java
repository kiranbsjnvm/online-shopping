package com.myweb.onlineshoppingBackend.test;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.myweb.onlineshoppingBackend.dao.CategoryDAO;
import com.myweb.onlineshoppingBackend.dto.Category;


public class CategoryTest {

	private static AnnotationConfigApplicationContext context ;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.myweb.onlineshoppingBackend");
		context.refresh();
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	
	/*
	@Test
	public void testAddCategory(){
		category = new Category();
		category.setName("sdfsdf");
		category.setDescription("This contains all kinds of televisions");
		category.setImageURL("sample1.img");
		
		boolean actual = categoryDAO.add(category);
		Assert.assertEquals(true, actual);
		
	}
	*/
	
	/*
	@Test
	public void testGet(){
		category = categoryDAO.get(3);
		Assert.assertEquals("Mobile", category.getName());
	}*/
	
	/*
	@Test
	public void testUpdate(){
		category = categoryDAO.get(3);
		category.setName("Mobile");
		
		boolean actual = categoryDAO.update(category);
		
		Assert.assertEquals(true, actual);
	}*/
	
	/*
	@Test
	public void testDelete(){
		category = categoryDAO.get(3);
		
		boolean actual = categoryDAO.delete(category);
		Assert.assertEquals(true, actual);
	}*/
	
	@Test
	public void testList(){
		
		List<Category> actual = categoryDAO.list();
		Assert.assertEquals(3, actual.size());
	}
}
