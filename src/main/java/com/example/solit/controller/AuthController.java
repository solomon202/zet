package com.example.solit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.solit.entity.User;
import com.example.solit.model.UserModel;
import com.example.solit.model.UserNumber;

import com.example.solit.model.Person;
//По результату успешно выполненной авторизации возвращаю данные авторизованного пользователя.
//анотации создают обьекты оживляя их говоря сервису спринг создает обьекты
@Controller
//контролеер это управление 
//название сервера через него набираются имена контролеров
//@RequestMapping("/start")
public class AuthController {
	//контролер парсит и кидает в методы модели .
	//Контроллер принимает запрос и вызывает соответствующий служебный метод, основанный на GET или POST. 
	//Вызванный метод определяет данные Модели, основанные на определённой бизнес-логике и возвращает в DispatcherServlet имя Вида (View).
  
	//для отправки данных на сайт
// Эта аннотация служит для маппинга запросов на классы-контроллеры и методы только сдвоеная pos get
    @RequestMapping(value = "/number",method = RequestMethod.POST)
	//модель передается методу в виде параметра.
	//model это мапа ключь значение 

    public String greetingSubmit(@RequestParam String number) {
    	  UserNumber numbers = new UserNumber(number);
    	  System.out.println(number);
    	return "sushi";
    }
	

	
	//для доступа к указанной странице
    
	  @GetMapping("/regist")
      public String  view() {
      	//имя предстовления формы 
          return "regist";
      }
	  
	//сылка на класс конкретный который обрабатывает запрос  
	    

	  
	   // Model (модель). Получает данные от контроллера, выполняет необходимые 
	    //операции и передаёт их в вид. View (вид или представление).
	    //модель из бд  в entity для передачи из html в модель 
	  //передачей данных от контроллера представлению при помощи модели в виде параметров
	  @GetMapping("/indexx")
	    public String index(Model model) {
//	     связь бд	получить индекс и вставвит в форму
	       // model.addAttribute();
	        return "indexx";
	    }
	  
	  
	  
	  
//        обработка конкретного урла  public String viewLoginPage()
        @GetMapping("/shop")
        //все методы в данном Контроллере относятся к URL-адресу 
        //Контроллер обрабатывает запрос и создает модель. 
        //Front-контроллер заполняет представление данными модели и возвращает полученный результат браузеру
        //создается бин с названием метода вашем 
        public String  viewLoginPage() {
        	//имя предстовления формы 
            return "sushi";
        }
        
        
        
    }
     

