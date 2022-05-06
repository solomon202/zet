package com.example.solit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Employe {
	//генерацыя номеров таблиы на возрастание 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

	//колонки таблицы 
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
	

	//набор метод доступа к поля таблицы 
    public long getId() {
		return id;
	}
	//набор метод доступа к поля таблицы 
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return username;
	}
	public void setFirstName(String username) {
		this.username = username;
	}
	public String getLastName() {
		return password;
	}
	public void setLastName(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
