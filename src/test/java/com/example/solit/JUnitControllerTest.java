package com.example.solit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@ContextConfiguration(classes = ElastSearchBootApplication.class)
import com.example.solit.controller.AuthController;
import com.example.solit.controller.HomeController;
//@RunWith необходима для включения тестирования интеграции Spring
//запустить тест 
//@RunWith (SpringRunner.class) обеспечивает мост между функциями тестирования Spring Boot и JUnit. Эта аннотация потребуется всякий раз, когда мы используем какие-либо функции тестирования 
//Spring Boot в наших тестах JUnit.Это обеспечивает полную поддержку загрузки контекста spring и внедрения зависимостей  тоесть бинов доступ к ним компонентов в тестах.
@RunWith(SpringRunner.class)

//анотацыя Чтобы протестировать компонент / компонент в приложении Spring Boot,
//@SpringBootTest нужен чтобы загрузит полный контекст приложения,и протестировать любойбин 
//обезательно название тестого  пакета должно быть такимже как и приложения !!!!!
@SpringBootTest
public class JUnitControllerTest {
	 @Autowired
	public  HomeController  homeController;
//@Test — определение данного метода в качестве тестируемого (по сути — метод, помеченный данной аннотацией и есть модульный тест).
    @Test
    public void testHomeController() {
    	//простой тест без загрузки контенера 
    	//создать обьект без запуска сприга если хотим запустить одельно 
   	//HomeController  homeController = new HomeController();
        String result = homeController.home();
        assertEquals(result, "Hello World!");
    }
}
