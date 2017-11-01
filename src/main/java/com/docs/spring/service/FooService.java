package com.docs.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docs.spring.model.FooModel;
import com.docs.spring.repository.IFooRepository;

@Service
public class FooService {

	@Autowired
	private IFooRepository fooRepository;

	public List<FooModel> findAll(int minAge, int maxAge) {
		return fooRepository.findAll(minAge, maxAge);
	}
	
	public FooModel save(FooModel foo) {
		return fooRepository.save(foo);
	}
}
