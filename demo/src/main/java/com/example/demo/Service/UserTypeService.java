package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.UserType;

public interface UserTypeService {

	void saveUserType(UserType userType);

	List<UserType> getAllUserTypes();


	UserType updateDepartment(UserType userType);

	void deleteUserType(long id);

	UserType findByuserType(String name);

	UserType getById(long id);

}
