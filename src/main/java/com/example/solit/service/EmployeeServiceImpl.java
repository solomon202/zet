package com.example.solit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.solit.entity.Employee;
import com.example.solit.repository.EmployeeRepository;
//сервис это вся бизнес логика наследуя рабочии методы реализацыя методов интерфейса 
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
//вернет список всех сотрудников контроллеру 
	@Override
	public List<Employee> getAllEmployees() {
		//вытащить с помощью репозитория и метода jpa  возвращяетнайденые записи 
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		//вставить в ентите  юзера с помощью репозитория 
		this.employeeRepository.save(employee);
	}
  //получить всех сотрудников  реализация интерфейса его методов по айди 
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	//реалезация 
	public void deleteEmployeeById(long id) {
		//а это уже конкретный метод через сылку репозиторий удаляет применяя методы jpa
		this.employeeRepository.deleteById(id);
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeRepository.findAll(pageable);
	}
}
