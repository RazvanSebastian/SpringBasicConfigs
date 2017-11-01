package com.docs.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="goo")
public class GooController {
	
	@GetMapping()
	public String getGoo() {
		return "goo test";
	}
	
}
