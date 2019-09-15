package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.UserType;
import com.example.demo.Repository.UserTypeRepository;
import com.example.demo.Service.UserTypeService;


@Service
@Transactional
public class UserTypeServiceImpl implements UserTypeService {
	
	@Autowired
	UserTypeRepository userTypeRepository;

	@Override
	public void saveUserType(UserType userType) {
		userTypeRepository.save(userType);
	}

	@Override
	public List<UserType> getAllUserTypes() {
		return (List<UserType>) userTypeRepository.findAll();
	}

	@Override
	public UserType getById(long id) {
		return userTypeRepository.findOne((int) id);
	}

	@Override
	public UserType updateDepartment(UserType userType) {
		return userTypeRepository.save(userType);
	}

	@Override
	public void deleteUserType(long id) {
		userTypeRepository.deleteById((int) id);
	}

	@Override
	public UserType findByuserType(String name) {
		return userTypeRepository.findByuserType(name);
	}

}