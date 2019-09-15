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

import com.example.demo.Model.UserType;
import com.example.demo.Service.UserTypeService;


@RestController
@RequestMapping(value = { "/userType" })
public class UserTypeController {

	@Autowired
	UserTypeService userTypeService;
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserType> getUserTypeById(@PathVariable("id") long id) {
		UserType userType = userTypeService.getById(id);

		if (userType == null)
			return new ResponseEntity<UserType>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<UserType>(userType, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<UserType> getAllUserTypes(){
		List<UserType> userTypes = userTypeService.getAllUserTypes();
		return userTypes;
	}
	
	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<UserType> createUserType(@RequestBody UserType userType, UriComponentsBuilder ucBuilder){
		UserType u = userTypeService.findByuserType(userType.getUserType());
		if(u == null) {
			userTypeService.saveUserType(userType);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/userType/{id}").buildAndExpand(userType.getUserTypeId()).toUri());
			return new ResponseEntity<UserType>(headers, HttpStatus.CREATED);
		}
		UserTypeController us = new UserTypeController();
		return us.getUserTypeById(userType.getUserTypeId());
		
	}
	
	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<String> updateUserType(@RequestBody UserType currentUserType){
		UserType userType = userTypeService.getById(currentUserType.getUserTypeId());
		if(userType == null)
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		
		userTypeService.updateDepartment(currentUserType);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<UserType> deleteUserType(@PathVariable("id") int id){
		UserType userType = userTypeService.getById(id);
		if(userType == null)
			return new ResponseEntity<UserType>(HttpStatus.NOT_FOUND);
		userTypeService.deleteUserType(id);;
		return new ResponseEntity<UserType>(HttpStatus.NO_CONTENT);
	}
}