package com.demo.spring;

import org.springframework.stereotype.Component;

@Component
public class EmployeeNotFoundException extends RuntimeException {
	int id=10;
	EmployeeNotFoundException()
	{
		super("Employee not found");
		System.out.println(id);
	}
}
