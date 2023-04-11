package com.siriuscom.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import com.siriuscom.springboot.thymeleafdemo.entity.Employee;
import org.springframework.stereotype.Component;


@Component
public interface EmployeeService {

	List<Employee> findAll();
	
	Employee findById(int theId);
	
	Optional<Employee> save(Employee theEmployee);
	
	void deleteById(int theId);
	
}
