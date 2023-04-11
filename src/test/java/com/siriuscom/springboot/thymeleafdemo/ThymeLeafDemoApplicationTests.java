package com.siriuscom.springboot.thymeleafdemo;


import com.siriuscom.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.siriuscom.springboot.thymeleafdemo.entity.Employee;
import com.siriuscom.springboot.thymeleafdemo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
class ThymeLeafDemoApplicationTests {


	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;



	/*
	Or this.
	@InjectMocks
	private EmployeeService employeeService = new EmployeeServiceImplementation();
	You need to mention the implemented class in the @InjectMocks or you need to instantiate your implemented class
	in the EmployeeService.
	 */







	@Test
	public void myFirstMockitoTestFindAll(){
		List<Employee> employees = new ArrayList<>();
		employees.add(new Employee("Avani", "Gupta", "avani@luv2code.com"));
		employees.add(new Employee("Surmani", "konsam", "blahblah@gmail.com"));
		when(employeeRepository.findAll()).thenReturn(employees);
		List<Employee> result = employeeRepository.findAll();
		assertThat(result).isEqualTo(employees);
		verify(employeeRepository,atLeastOnce()).findAll();
		//we should and only should play with our mocked objects.
	}


	@Test
	public void findWithSpecificId(){
		int id = 6;
		Employee employeeToGet = new Employee("Surmani","konsam","blahblah@gmail.com");
		when(employeeRepository.findById(id)).thenReturn(Optional.of(employeeToGet));
		assertEquals(employeeService.findById(id),employeeToGet);
	}



	@Test
	public void testingDeleteMethod(){
		// given
		int employeeIdToDelete = 4;

		// when;
		employeeRepository.deleteById(employeeIdToDelete);

		// then
		verify(employeeRepository, atLeastOnce()).deleteById(employeeIdToDelete);

		/*
		verify must and only must take mock as its first argument, followed by the number of times the method must
		have been called
		 */
	}


	@Test
	public void updateObjectBasedOnId(){
		/*
		mockito is all based on the type of response we are expecting right.
		We are already expecting the response from the mocked object.
		The response is however our own mocked response.
		and verify will therefore be used, to confirm whether in the process the actual method or api was called or not.
		 */
		Employee employeeToSave = new Employee("adam","smith","smith@gmail.com");
		when(employeeRepository.save(employeeToSave)).thenReturn(employeeToSave);
		Employee expectedData = employeeRepository.save(employeeToSave);
		assertThat(expectedData).isEqualTo(employeeToSave);
		verify(employeeRepository,atLeastOnce()).save(employeeToSave);
	}






	@Test
	public void sayTrue(){
		Assertions.assertTrue(true);
	}



}
