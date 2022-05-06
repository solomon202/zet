package com.example.solit.config;

import com.example.solit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//включить безопасность спринг секюрите если не включить адаптер небудет наследоватся
@EnableWebSecurity(debug = true)

@Profile("dao")//spring.profiles.active - одно из свойств,подключается в конфигурациии при сборки приложения
//наследуемся от одаптара безопасности вызывая методы для переопределения для настройки безопасности
//куда есть доступ куда нет доступа куда нужно вбивать пороль пользователь все настройки безопасности происходят в этом класе 
public class DaoSecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(DaoSecurityConfig.class.getName());
    //юзер сервис инжектим в конфиг для получения доступа к ниму 
    private UserService userService;

    @Autowired
    public void setUserDetailsService(UserService userService) {
        this.userService = userService;
    }
//вытаскиваем методы из класса http
//    передает на вход обьект 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Dao Authentication Provider");
      //Это строкой мы говорим предоставить разрешения для следующих url.
           http
           
             //защита от вставок левых сайтов
              .csrf().disable()
              .authorizeRequests()
              
              .antMatchers("/**").permitAll()
             //.authorizeRequests().anyRequest().authenticated()//Декларирует, что все запросы к любой конечной точке должны быть авторизованы, иначе они должны быть отклонены.
        //уровни защищености фильтры 
        //если пользователь переходт по этой ссылке то тоько пускать индефецированых
                //получения ответа 
//  вошли в систему под чиим именем      (вставили и повернули — прошли идентификацию) ПОД юзер или админ      
                .antMatchers("/dao").authenticated()
              //суда пускать только того у кого есть роль
                //роли допуска кого можно пускать юзер или админа имеет роли 
                .antMatchers("/shop").hasAnyRole("USER")
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN") // ROLE_ADMIN, ROLE_SUPERADMIN
                .anyRequest().permitAll()
                .and()
              //индификация через форму или указать на свою форму Ввод логина и пароля в форму считается аутентификциейс адресом шаблона
    			.formLogin()
    			//http /login формы для вода логин пароль на безопасность 
    			.loginPage("/regist").permitAll()
                //Перенарпавление на главную страницу после успешного входа
                 .defaultSuccessUrl("/shop");
    				
    }        
                
       //преобразователь паролей кодирует пароли силой 12
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

        @Bean
        //запросить пароль и имя 
        //задача провайдера произвести аудификацию если юзер существует положи его в спринг секюрити контекст получает токен
        //проверка пароля отдаем ему пароль его задача сверить существует такой пароль или не существует
        //если существует его нужно положить спринг секюрити контекст 
        //вытащить в userService пароль
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setPasswordEncoder(passwordEncoder());
            authenticationProvider.setUserDetailsService(userService);
            return authenticationProvider;
        }
    }