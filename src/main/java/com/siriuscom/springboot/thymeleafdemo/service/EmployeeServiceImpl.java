package com.siriuscom.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import com.siriuscom.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.siriuscom.springboot.thymeleafdemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//this is our system under test, one which we will be doing test on.
@Service
public class EmployeeServiceImpl implements EmployeeService {



	//and this is our mock object, one which we will be mocking of and test if the mocked object gives us the desired
	//result. The desired result will be known as "stubbing". Let's see what it is?
	@Autowired
	private EmployeeRepository employeeRepository;

	
	@Override
	public List<Employee> findAll() {
		System.out.println(employeeRepository.findAllByOrderByLastNameAsc());
		//if tested then it will return our mocked data.
		return employeeRepository.findAll();

		/*
		A weird error has been found.
		When I tried to use findAll of my mockedRepository, the test case fails if findAllByOrderByLastNameAsc is used.
		However, if the default findAll() is used here, it doesn't why is that.

		 */
	}



	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee theEmployee = null;
		
		if (result.isPresent()) {
			theEmployee = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theEmployee;
	}

	@Override
	public Optional<Employee> save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
		int id = theEmployee.getId();
		System.out.println("id: "+id);
		if(employeeRepository.findById(id).isPresent()){
			return employeeRepository.findById(id);
		}

		return null;
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}






