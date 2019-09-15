package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Department;

public interface DepartmentService {

	void saveDepartment(Department department);

	List<Department> getAllDepartments();

	Department getById(long id);

	Department updateDepartment(Department department);

	void deleteDepartment(long id);

}
