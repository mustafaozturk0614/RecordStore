package com.bilgeadam.recordshop.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SingeltonLogger {
	private static SingeltonLogger instance;
	
	private SingeltonLogger() {
		
	}
	
	public static SingeltonLogger getInstance() {
		if (instance == null) {
			instance = new SingeltonLogger();
			
		}
		return instance;
	}
	
	public Logger getLogger(Object object) {
		final Logger logger = LogManager.getLogger(object.getClass());
		return logger;
		
	}
}
