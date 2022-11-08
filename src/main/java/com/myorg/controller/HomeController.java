package com.myorg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController 
{
	@GetMapping("/")
   public String showHomePage() 
   {
	   String msg = "hello world";
	   String msg2 = " added test msg";
	   return msg+msg2;
   }
}
