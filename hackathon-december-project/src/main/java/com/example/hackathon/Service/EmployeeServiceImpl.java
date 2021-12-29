package com.example.hackathon.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hackathon.Model.Employee;
import com.example.hackathon.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}
	
	@Override
	public void saveEmployee(Employee employee)
	{
		this.employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		
		if(optional.isPresent())
		{
			employee=optional.get();
		}
		else {
			throw new RuntimeException("Employee not found by Id : " + id);
			
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}

	@Override
	public void deleteAllEmployees(Employee employee) {
		this.employeeRepository.deleteAll();
		
	}

}
