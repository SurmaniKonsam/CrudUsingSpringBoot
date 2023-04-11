package com.siriuscom.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siriuscom.springboot.thymeleafdemo.entity.Employee;
import java.util.List;



public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!

    public List<Employee> findAllByOrderByLastNameAsc();
	
}
