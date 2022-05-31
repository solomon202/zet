package com.example.solit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.solit.entity.Employee;
import com.example.solit.service.EmployeeService;

@Controller
public class EmployeeController {
//Аннотация @Autowired отмечает конструктор, поле или метод как требующий автозаполнения
	// получение или отослать работа с методами сервиса
	@Autowired
	private EmployeeService employeeService;

	// отобразить список сотрудников в шаблон и уже шаблон отоброзиться в браузере
	@GetMapping("/")
	// уровень педстовления нужна Model а для обработки вставок в щаблон
	// уже создаём сами обьект модель
	// интерфейс предназначен для добавления атрибутов в модель передачи из html в
	// модель
	public String viewHomePage(Model model) {
		// вставить в атребуты данные с помощью модели
		// сортировка по отребутом обьекта
		// переход поле сортировки в изночальное состояне ASC – (по умолчанию,
		// необязательный). Сортирует набор в порядке возрастания или по алфавиту.
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/inde")
	// метод контроллера в параметрах модель нужна для вставок в шаблон
	public String wer(Model model) {
		// метод модели в параметрах вставляем атребут куда вставвит и метод сервис
		// запус этого метода в нутри которого исполнительный код
		// метод сервиса
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "inde";
	}

	// далее в сервис
	// далее в репозиторий
	// в бд
	// из базы дынные попадают врепозиторий

	// добовление нового сотрудника в шаблоне сылка на этот метод
	// показать Новую Форму Сотрудника
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// модель возвращать не полностью entity, а только что мы хотим вернуть или как
		// то обработать, без бизнес логики.
		// ентити связана жостко с бд и возвращает все колонки
		// создали модели вели данные нажали на кнопку и втставили данных форму
		Employee employee = new Employee();
		// в модель атребут и обьект как ключь и значение,все веденные данные
		model.addAttribute("employee", employee);
		// при нажати перемещяемся в шаблон post сохранение
		return "new_employee";
	}

	// сохранить
	@PostMapping("/saveEmployee")
	// @ModelAttribute("employee")вкачестве аргумента метода он указывает, что
	// аргумент должен быть извлечен из модели
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// сохранить сотрудника в базе данных
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}

	// обновить изменить юзера и вернуть в базу изменение
	@GetMapping("/showFormForUpdate/{id}")
	// @PathVariable Когда нам нужно извлечь значения из URI запроса
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

		// // получить сотрудника из сервиса
		Employee employee = employeeService.getEmployeeById(id);

		// установите employee в качестве атрибута модели для предварительного
		// заполнения формы
		model.addAttribute("employee", employee);
		return "update_employee";
	}

	// удалить
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {

		// вызов метода удаления сотрудника
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}

	// сортировка следующий последний
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
}
