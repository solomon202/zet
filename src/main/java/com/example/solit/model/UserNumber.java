package com.example.solit.model;
//формируется обьект на уровне фронт береходит в бэк и уже пересылается между процесами.

//ентити только модель  как и сбазой получить что конкретное для гибкой работы 
public class UserNumber {
   //создаем обьект
	//сылка на обьект
	
	String number;
	
	UserNumber(){
	}
	
	public UserNumber(String number){
		this.number = number;
		}
	
    //получили сылку со значением 
	public String getNumber() {
        return number;
    }
 //втавили обьект и его параметры в метод обьекта usernumber
    public void setNumber(String number) {
        this.number = number;
    }

  
	}


