package com.example.demo.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.Model.Department;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Service.DepartmentService;



@RestController
@RequestMapping(value = { "/department" })
public class DepartmentController{

	@Autowired
	DepartmentService departmentService;
	@Autowired
	DepartmentRepository departmentRepository;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> getDepartmentById(@PathVariable("id") long id) {
		Department department = departmentService.getById(id);

		if (department == null)
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Department> getAllDepartments(){
		List<Department> departments = departmentService.getAllDepartments();
		return departments;
	}
	
	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Void> createDepartment(@RequestBody Department department, UriComponentsBuilder ucBuilder){
		Department d = departmentRepository.findBydeptName(department.getDeptName());
		System.out.println(d);
		if(d == null) {
			departmentService.saveDepartment(department);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/department/{id}").buildAndExpand(department.getDeptId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return null;
		
	}
	
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateDepartment(@RequestBody Department currentDepartment){
		Department department = departmentService.getById(currentDepartment.getDeptId());
		if(department == null)
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		
		departmentService.updateDepartment(currentDepartment);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Department> deleteDepartment(@PathVariable("id") int id){
		Department department = departmentService.getById(id);
		if(department == null)
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		departmentService.deleteDepartment(id);
		return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
	}


}