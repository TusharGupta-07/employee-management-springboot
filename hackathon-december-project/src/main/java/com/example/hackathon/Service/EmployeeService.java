package com.example.hackathon.Service;

import java.util.List;
import com.example.hackathon.Model.Employee;
public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id); 
	void deleteAllEmployees(Employee employee);
}
