package com.example.demo.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Model.UserType;


public interface UserTypeRepository extends CrudRepository<UserType, Integer>{
	UserType findByuserType(String name);

	@Query("select u from UserType u where u.userTypeId=?1")
	UserType findOne(int id);


}