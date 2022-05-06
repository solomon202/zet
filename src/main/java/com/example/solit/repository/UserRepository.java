package com.example.solit.repository;
//позволит оперировать объектом в БД.
//интерфейс который взаимодеиствует с базой и далее с сущьностью
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.solit.entity.User;

import java.util.Optional;
// интерфейс Это совокупность абстрактных методов.
@Repository
@Profile("dao")
//CrudRepository имеет набор базовых методов для работы с сущностью,
//названия которых говорят сами за себя:
//Имя репозитория должно начинаться с имени сущности
//JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор стандартных методов JPA для работы с БД.
// Второй Generic должен быть оберточным типом того типа которым есть ID нашей сущности (обязательно).

public interface UserRepository extends CrudRepository<User, Long> {
//отдает метод сервису распарсит хочу искать по имени пользователя 
	//хороший тон разделя методы и переносить в отдельный интерфейс 
    Optional<User> findByUsername(String username);
}
//Основное понятие в Spring Data — это репозиторий. Это несколько интерфейсов которые используют JPA
//Entity для взаимодействия с ней.как один механизм с другим
//Первый Generic должен быть объектом нашей сущности для которой мы создали Repository, это указывает на то, что Spring Data должен предоставить реализацию методов для работы с этой сущностью (обязательно).
//4 – Мы должны унаследовать свой интерфейс от JpaRepository, иначе Spring Data не предоставит реализацию для нашего репозитория (обязательно).
