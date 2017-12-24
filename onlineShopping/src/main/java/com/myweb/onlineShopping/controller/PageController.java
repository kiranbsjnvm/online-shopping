package com.myweb.onlineShopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myweb.onlineShopping.exception.ProductNotFoundException;
import com.myweb.onlineshoppingBackend.dao.CategoryDAO;
import com.myweb.onlineshoppingBackend.dao.ProductDAO;
import com.myweb.onlineshoppingBackend.dto.Category;
import com.myweb.onlineshoppingBackend.dto.Product;
 
@Controller 
public class PageController 
{
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value={"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "Home");
		
		logger.info("Inside Page controller index method - Info");
		logger.debug("Inside Page controller index method - Debugg");
		
		//passing category list
		modelAndView.addObject("categories", categoryDAO.list());
		
		modelAndView.addObject("userClickHome", true);
		return modelAndView;
	}
	
	@RequestMapping(value={"/about"})
	public ModelAndView about()
	{
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "About Us");
		modelAndView.addObject("userClickAbout", true);
		return modelAndView;
	}
	
	@RequestMapping(value={"/contact"})
	public ModelAndView contact()
	{
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "Contact Us");
		modelAndView.addObject("userClickContact", true);
		return modelAndView;
	}
	
	@RequestMapping(value="/show/all/products")
	public ModelAndView showAllProducts()
	{
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "All Products");
		
		//passing category list
		modelAndView.addObject("categories", categoryDAO.list());
		
		modelAndView.addObject("userClickAllProducts", true);
		return modelAndView;
	}
	
	@RequestMapping(value="/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id)
	{
		ModelAndView modelAndView = new ModelAndView("page");
		
		/* Fetching products of category by id*/
		Category category = null;
		category = categoryDAO.get(id);
		
		modelAndView.addObject("title", category.getName());
		
		//passing category list
		modelAndView.addObject("categories", categoryDAO.list());
		
		//passing category 
		modelAndView.addObject("category", category);
		
		modelAndView.addObject("userClickCategoryProducts", true);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException
	{
		ModelAndView modelAndView = new ModelAndView("page");
		
		Product product = productDAO.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		
		//updating view count column
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		
		modelAndView.addObject("title",product.getName());
		modelAndView.addObject("product",product);
		modelAndView.addObject("userClickShowProduct", true);
		
		return modelAndView;
	}
}
