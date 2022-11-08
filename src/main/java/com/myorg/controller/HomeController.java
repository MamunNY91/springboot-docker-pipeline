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
	@GetMapping("/product")
	   public String showProduct() 
	   {
		   String p1 = "Laptop";
		   String p2 = " Tablet";
		   return p1+p2;
	   }
	@GetMapping("/user")
	   public String showUser() 
	   {
		   String u1 = "User1";
		   String u2 = " User2";
		   return u1+u2;
	   }
	@GetMapping("/country")
	   public String showCountry() 
	   {
		   String c1 = "Bangladesh";
		   String c2 = " USA";
		   return c1+c2;
	   }
   
}
