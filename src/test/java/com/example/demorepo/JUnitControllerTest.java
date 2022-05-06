package com.example.demorepo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.solit.controller.AuthController;
import com.example.solit.controller.HomeController;

public class JUnitControllerTest {
	  @Autowired
	public  HomeController  homeController;

    @Test
    public void testHomeController() {
    //	HomeController  homeController = new HomeController();
        String result = homeController.home();
        assertEquals(result, "Hello World!");
    }
}

