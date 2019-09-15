package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.Department;



public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	Department findBydeptName(String deptName);

	@Query("select u from Department u where u.deptId=?1")
	Department findOne(int id);


}
