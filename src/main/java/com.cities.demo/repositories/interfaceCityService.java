package com.cities.demo.repositories;

import com.cities.demo.classes.city;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface interfaceCityService extends CrudRepository<city, Long> {

	public List<city> findAll();

	@Query("SELECT c FROM city c WHERE c.id = 1")
	public List<city> findById();
}