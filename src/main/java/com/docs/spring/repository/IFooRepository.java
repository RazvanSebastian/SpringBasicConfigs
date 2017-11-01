package com.docs.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.docs.spring.model.FooModel;

@Repository
public interface IFooRepository extends JpaRepository<FooModel, Long> {

	@Query("select foo from FooModel foo where foo.age between :minAge and :maxAge")
	public List<FooModel> findAll(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

}
