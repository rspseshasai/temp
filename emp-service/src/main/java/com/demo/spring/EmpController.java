package com.demo.spring;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.entity.Emp;

@Controller
public class EmpController {

	@Autowired
	EmpRepository repo;
	@Autowired
	EmpDataValidator validator;
	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String loadIndexPage()
	{
		return "index";
	}
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String getFirstPage(Model model) {
		Emp e = new Emp();
		model.addAttribute("myemp", e);
		return "emp";
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute("myemp") Emp e, BindingResult errors) {
		ModelAndView mv = new ModelAndView();
		validator.validate(e, errors);
		if (errors.hasErrors()) {
			mv.setViewName("emp");
		} else {
			if (repo.existsById(e.getId())) {
				mv.setViewName("error");
			} else {
				repo.save(e);
				mv.setViewName("response");
				mv.addObject("name", e.getName());
			}
		}
		return mv;
	}
	@RequestMapping(path = "/findEmp", method = RequestMethod.POST)
	public ModelAndView findEmp(@RequestParam("id") int id)
	{
		ModelAndView mv=new ModelAndView();
		java.util.Optional<Emp> o=repo.findById(id);
		if(o.isPresent())
		{
			mv.addObject("result",o.get());
			mv.setViewName("empdetails");
		}
		else
		{
			mv.setViewName("error");
		}
		return mv;
	}
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public ModelAndView getEmployees()
	{
		ModelAndView mv=new ModelAndView();
		ArrayList<Emp> elist=(ArrayList<Emp>) repo.findAll();
		mv.addObject("emplist",elist);
		mv.setViewName("list");
		return mv;
	}
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ModelAndView exception(EmployeeNotFoundException ex)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("errors");
		mv.addObject("message", ex.getMessage());
		return mv;
	}
}