package com.myweb.onlineShopping.utility;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	
	private static final String ABS_Path = "D:\\Java_Workspace\\online-shopping\\onlineShopping\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_Path = "";

	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		
		REAL_Path = request.getSession().getServletContext().getRealPath("/assets/images/");
		
		logger.info(REAL_Path);
		
		if(!new File(ABS_Path).exists()){
			new File(ABS_Path).mkdirs();
		}
		
		if(!new File(REAL_Path).exists()){
			new File(REAL_Path).mkdirs();
		}
		
		try {
			//transfer to server
			file.transferTo(new File(REAL_Path + code + ".jpg"));
			
			//transfer to project directory
			file.transferTo(new File(ABS_Path + code + ".jpg"));
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
