package com.myweb.onlineshoppingBackend.test;

import java.util.List;

import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.myweb.onlineshoppingBackend.dao.ProductDAO;
import com.myweb.onlineshoppingBackend.dto.Product;


public class ProductTest {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;
	
	@BeforeClass
	public static void initialize(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.myweb.onlineshoppingBackend");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
	/*
	@Test
	public void testCRUDProduct() {
		
		//adding new product
		product = new Product();
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("This is some description for oppo mobile phones!");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		boolean actual = productDAO.add(product);
		Assert.assertEquals(true, actual);
		
		
		// reading and updating the category
		product = productDAO.get(2);
		product.setName("Samsung Galaxy S7");
		
		boolean actual2 = productDAO.update(product);
		Assert.assertEquals(true, actual2);
		
		
		//soft deleting product
		boolean actual3 = productDAO.delete(product);
		Assert.assertEquals(true, actual3);
		
		//getAllProducts method
		List<Product> actualList = productDAO.list();
		Assert.assertEquals(6, actualList.size());
		
	}*/
	
	@Test
	public void testListActiveProducts() {
		List<Product> actualList = productDAO.listActiveProducts();
		Assert.assertEquals(5, actualList.size());			
	} 
	
	
	@Test
	public void testListActiveProductsByCategory() {
		List<Product> actualList = productDAO.listActiveProductsByCategory(1);
		Assert.assertEquals(2, actualList.size());	
		
		List<Product> actualList2 = productDAO.listActiveProductsByCategory(3);
		Assert.assertEquals(3, actualList2.size());	
		
	} 
	
	@Test
	public void testGetLatestActiveProduct() {
		List<Product> actualList = productDAO.getLatestActiveProducts(3);
		Assert.assertEquals(3, actualList.size());	
		
	}
}
