package com.demo.spring;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demo.spring.entity.Emp;
@Component
public class EmpDataValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Emp.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Emp e=(Emp)target;
		if(e.getId()<1)
		{
			errors.rejectValue("empId", "invalid id", "id is not valid");
		}
		if(e.getName().isEmpty())
		{
			errors.rejectValue("name", "invalid name", "name cant be empty");
		}
       if(e.getCity().isEmpty())
    	   errors.rejectValue("city", "invalid city", "city cant be empty");
       if(e.getSalary()<5000)
       {
    	   errors.rejectValue("salary", "invalid salary", "salary cant be less than 5000");
       }
	}

}
