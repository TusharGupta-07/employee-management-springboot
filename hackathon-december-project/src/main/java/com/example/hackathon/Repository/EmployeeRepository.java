package com.example.hackathon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hackathon.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
