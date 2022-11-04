package com.myorg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController 
{
	@GetMapping("/")
   public String showHomePage() 
   {
      
	   String msg = "hello simul,";
	   String msg2 = "Trying out Git stash";

	   return msg+msg2;
   }
}
