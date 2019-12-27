package com.example.demo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class MessageResponse {
	
	@Value("${codeSuccess}")
	private String codeSuccess;
	
	@Value("${codeError}")
	private String codeError;
		
	@Value("${mSuccess}")
	private String mSuccess;
	
	@Value("${mEmptyName}")
	private String mEmptyName;
	
	@Value("${mGetList}")
	private String mGetList;
	
	@Value("${mEmptyId}")
	private String mEmptyId;
		
	@Value("${mNotExist}")
	private String mNotExist;


	
	public String codeSuccess() {
		return codeSuccess;
		
	}
	
	public String codeError() {
		return codeError;
		
	}
	
	
	
	public String mSuccess() {		
		return mSuccess;
	}
	
	
	public String mEmptyName() {		
		return mEmptyName;
	}
	
	public String mGetList() {		
		return mGetList;
	}
	
	
	public String mEmptyId() {		
		return 	mEmptyId;
	}
	
	public String mNotExist() {		
		return mNotExist;
	}
	

}
