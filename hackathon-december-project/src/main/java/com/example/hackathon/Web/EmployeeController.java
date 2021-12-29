package com.example.hackathon.Web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hackathon.Model.Employee;
import com.example.hackathon.Service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String showHomePage(Model model)
	{
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "home";
	}

	@GetMapping("/addemployee")
	public String showAddNewEmployeePage(Model model)
	{
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "addemployee";
	}
	
	@PostMapping("/saveemployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee)
	{
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/updateemployee/{id}")
	public String saveEmployee(@PathVariable(value="id") long id, Model model)
	{
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);

		return "updateemployee";
	}
	
	@GetMapping("/deleteemployee/{id}")
	public String saveEmployee(@PathVariable(value="id") long id)
	{
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	@GetMapping("/removeallemplyees")
	public String removeAllEmployes()
	{
		Employee employee = new Employee();

		employeeService.deleteAllEmployees(employee);

		return "redirect:/";
	}
}
