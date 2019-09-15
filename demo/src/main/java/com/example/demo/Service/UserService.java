package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.User;

public interface UserService {

	void createUser(User user);


	List<User> getAllUsers();

	void updateUser(User user);

	void deleteUser(int id);


	User getUserById(long id);

}
