package com.myweb.onlineShopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myweb.onlineShopping.utility.FileUploadUtility;
import com.myweb.onlineshoppingBackend.dao.CategoryDAO;
import com.myweb.onlineshoppingBackend.dao.ProductDAO;
import com.myweb.onlineshoppingBackend.dto.Category;
import com.myweb.onlineshoppingBackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProduts(@RequestParam(name="operation",required=false) String operation){
		
		ModelAndView modelAndView = new ModelAndView("page");
		
		modelAndView.addObject("userClicksManageProducts",true);
		modelAndView.addObject("title", "Manage Products");
		
		Product nProduct = new Product(); 
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		modelAndView.addObject("product", nProduct);
		
		if(operation!=null){
			if(operation.equals("product")){
				modelAndView.addObject("message", "Product Submission Succsfull");
			}
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/product",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,HttpServletRequest request){
		
		if(results.hasErrors())
		{
			model.addAttribute("userClicksManageProducts",true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Products Submition");
			
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		productDAO.add(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	@ModelAttribute("categories")
	public List<Category> listCategory(){
		
		return categoryDAO.list();
		
	}
	
}
