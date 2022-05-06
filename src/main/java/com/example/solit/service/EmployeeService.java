package com.example.solit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.solit.entity.Employee;
//методы для работы сбазой 
public interface EmployeeService {
	//получить всех сотрудников из юзера mployee и втавляем методы юзера в форму 
	List<Employee> getAllEmployees();
	
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
