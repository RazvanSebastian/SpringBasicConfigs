package com.docs.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.docs.spring.model.FooModel;
import com.docs.spring.service.FooService;

@RestController
@RequestMapping(value = "/foo")
public class FooController {
	
	private static final Logger LOGGER = Logger.getLogger(FooController.class);

	@Autowired
	private FooService fooService;

	@GetMapping
	public ResponseEntity<?> getFoo(@RequestParam(name = "minAge", required = true) int minAge,
			@RequestParam(name = "maxAge", required = true) int maxAge) {
		LOGGER.info("Into FooController -> getFoo() -> Response OK");
		return new ResponseEntity<>(fooService.findAll(minAge, maxAge), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody(required = true) FooModel foo) {
		LOGGER.info("Into FooController -> create() -> Response OK");
		return new ResponseEntity<>(fooService.save(foo), HttpStatus.CREATED);
	}
}
