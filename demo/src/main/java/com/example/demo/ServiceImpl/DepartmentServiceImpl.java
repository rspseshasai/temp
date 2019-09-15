package com.example.demo.ServiceImpl;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Department;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Service.DepartmentService;



@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public void saveDepartment(Department department) {
		departmentRepository.save(department);
	}

	@Override
	public List<Department> getAllDepartments() {
		return (List<Department>) departmentRepository.findAll();
	}

	@Override
	public Department getById(long id) {
		return departmentRepository.findOne((int) id);
	}

	@Override
	public Department updateDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public void deleteDepartment(long id) {
		departmentRepository.deleteById((int) id);
	}

}
