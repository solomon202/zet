package com.example.solit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.solit.controller.AuthController;
import com.example.solit.controller.EmployeeController;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//позволяет подключить спринг в тесты. 
@ExtendWith(SpringExtension.class)
//подключение к проверяемым на тест классам 
// Простое присутствие @WebAppConfiguration в тестовом классе гарантирует, что WebApplicationContext будет загружен для теста с использованием значения по умолчанию 
//это нужно ApplicationContext — это главный интерфейс в Spring-приложении, который предоставляет информацию о конфигурации приложения.
// Собственно, контекст создаёт и хранит экземпляры классов вашего приложения, определяет их зависимости друг с другом и автоматически их задаёт.
@WebAppConfiguration
public class MainControllerTest {
//Бин — создаваемый Spring-ом объект класса, который можно внедрить в качестве значения поля в другой объект.
	// Эта операция называется «инжектнуть» (inject).внедрить
	@Autowired
	private EmployeeController employeeController;

	@Autowired
	private AuthController authController;

//через него делаем запросы к контроллерам 
	private MockMvc mvc;

//Аннотацией @BeforeEach помечаются методы, которые будут выполняться перед стартом каждого из тестовых методов.
	// делаем какуюто подготовку
	@BeforeEach
	// Это говорит Spring, чтобы внедрить WebApplicationContext он создал ранее, в
	// ваш тест. Затем это используется в очень простом однострочном методе setup()
	// :
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) employeeController).build();
	}

//Аннотация @Test используется для указания того, что аннотированный метод является методом тестирования.
//@Методы тестирования не должны быть частными или статическими. Методы @Test не должны возвращать значение.
//@Методы тестирования могут при необходимости объявлять параметры, которые должны быть разрешены с помощью решателей параметров.
	@Test
	public void homeShouldRedirectToUsersPage() throws Exception {
		mvc.perform(get("/shop")).andDo(print()).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/sushi"));
	}
	// MockMvc состоит из 3 шагов: построение мок-объекта,
	// отправка HTTP-запроса контроллеру и собственно анализ результатов.

	@Test
	public void viewShouldReturnGreetingWithSpecifiedName() throws Exception {
		String name = "stranger";
		String expected = "Привет " + name;

		mvc.perform(get("/view/{name}", name)).andExpect(status().isOk()).andExpect(model().attribute("msg", expected))
				.andExpect(view().name("/index"));
	}

	@Test
	public void rawShouldReturnResponseBody() throws Exception {
		mvc.perform(get("/raw")).andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(TEXT_PLAIN))
				.andExpect(content().string("Raw data"));
	}
}
