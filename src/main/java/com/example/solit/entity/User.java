package com.example.solit.entity;
//JPA : Знакомство с технологией - 2ORM — это по сути концепция о том, что Java объект можно представить как данные в БД (и наоборот). 
//Она нашла воплощение в виде спецификации JPA — Java Persistence API.
import lombok.Data;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import java.util.Collection;
//сгенерировать сущьность персонаж в бд 
@Entity
//то связь между базой данной.
//записать в таблицу получить.обеспечивает основные операции по поиску, сохранения, удалению данных
//миграции это контроль изменеия версий базы данных и внисения изменений в базу внося новые поля(поле не меняется через entity нернерится новое)
//и колонки обновляем в пропертях spring.jpa.hibernate.ddl-auto=create
//Hibernate сущность, которая мапится на нашу таблицу, будет выглядеть так:
//сущьность задачи 
//создается в базе данных и управляется с помощью spring.jpa.hibernate.ddl-auto=none
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
  //связь с другой таблицей удаление всех таблиц при стирании пользователя 
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}