package com.example.solit;
//запуск приложения вод запроса .запуск ожидание на сервере 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Для запуска одной из версий безопасности, в Edit Configurations > VM Options
	// указываете: -Dspring.profiles.active=Имя_профиля
@SpringBootApplication
public class SolitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolitApplication.class, args);
	}

}
