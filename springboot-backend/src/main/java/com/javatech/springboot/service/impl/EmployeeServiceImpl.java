package com.javatech.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javatech.springboot.exception.ResourceNotFoundException;
import com.javatech.springboot.model.Employee;
import com.javatech.springboot.repository.EmployeeRepository;
import com.javatech.springboot.service.EmployeeService;

@Service
//@Transational : no need to add  (1)
public class EmployeeServiceImpl implements EmployeeService {

	//@Autowired : dont need to add if it has only one constructor (2)
	private EmployeeRepository employeeRepository;
	
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	// to save emp
	
	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	//To get all emp
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		/*
		 * Optional<Employee> employee = employeeRepository.findById(id);
		 * 
		 * if(employee.isPresent()) { return employee.get(); }else { throw new
		 * ResourceNotFoundException("Employee","Id",id); }
		 */
		
		return employeeRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));
		
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		
		// we need to check, employee with given id existing or not
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
									() -> new ResourceNotFoundException("Employee","Id",id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//save existing emp to db
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		
		//Check employee with given is exist or not in DB
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
		
		employeeRepository.deleteById(id);
		
	}

}
