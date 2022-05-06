package com.example.solit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.solit.entity.Employee;
//репозиторий соеденяется сбазой 
//вставляем пользователя и ади как первичьный ключ
//JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
